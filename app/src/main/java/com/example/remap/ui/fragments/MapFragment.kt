package com.example.remap.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.yandex.mapkit.search.SearchManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.remap.R
import com.example.remap.core.RemapClient
import com.example.remap.core.util.Resource
import com.example.remap.core.util.copyToClipboard
import com.example.remap.core.util.getBitmapFromVectorDrawable
import com.example.remap.core.util.toCoordinateFormat
import com.example.remap.data.repository.RecyclePointRepositoryImpl
import com.example.remap.databinding.FragmentMapBinding
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.ui.viewmodels.MapFragmentViewModel
import com.example.remap.ui.viewmodels.ViewModelProviderFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Address
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.mapkit.search.ToponymObjectMetadata
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    private lateinit var viewModel: MapFragmentViewModel
    private lateinit var mapView: MapView
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var placeMarkDetailsBottomSheet: FrameLayout
    private lateinit var placeMarkDetailsBottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private lateinit var placeMarkSetUpBottomSheet: FrameLayout
    private lateinit var placeMarkSetUpBottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private var recyclePointList: List<RecyclePoint> = listOf()
    private lateinit var recyclePinsCollection: MapObjectCollection
    private var markerList: MutableList<PlacemarkMapObject> = mutableListOf()

    private lateinit var searchManager: SearchManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //Map initializing
        MapKitFactory.initialize(requireContext())
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.mapview
        val map = mapView.mapWindow.map
        mapInitialize(map)

        //defining SearchManager
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)

        //Bottom sheet to set up placemark
        placeMarkSetUpBottomSheet = view.findViewById(R.id.placemark_setup_bottom_sheet)
        placeMarkSetUpBottomSheetBehavior = BottomSheetBehavior.from(placeMarkSetUpBottomSheet)
        placeMarkSetUpBottomSheetSetUp(placeMarkSetUpBottomSheetBehavior)

        // Bottom sheet to display placemark info
        placeMarkDetailsBottomSheet = view.findViewById(R.id.placemark_info_bottom_sheet)
        placeMarkDetailsBottomSheetBehavior = BottomSheetBehavior.from(placeMarkDetailsBottomSheet)
        placeMarkDetailsBottomSheetSetUp(placeMarkDetailsBottomSheetBehavior)

        val mapObjectTapListener = MapObjectTapListener { p0, p1 ->
            val placeMarkData = (p0.userData as RecyclePoint)
            setPlaceMarkBottomSheetDetails(placeMarkData)
            placeMarkDetailsBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            true
        }

        val bitmap = requireContext().getBitmapFromVectorDrawable(R.drawable.ic_map_pin)
        val imageProvider = ImageProvider.fromBitmap(bitmap)

        recyclePinsCollection = map.mapObjects.addCollection()
        recyclePinsCollection.addTapListener(mapObjectTapListener)

        val repository = RecyclePointRepositoryImpl(RemapClient.provideRemapRecycleAPI())
        val viewModelProviderFactory = ViewModelProviderFactory(repository)

        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MapFragmentViewModel::class.java)
        viewModel.recyclePointResponse.observe(
            viewLifecycleOwner,
            Observer { recyclePointResponse ->
                when (recyclePointResponse) {
                    is Resource.Success -> {
                        recyclePointResponse.data?.let {
                            recyclePointList = it
                            placemarkInitialize(recyclePointList, imageProvider)
                        }
                    }

                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Data is loading", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            recyclePointResponse.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    private fun placeMarkDetailsBottomSheetSetUp(bottomSheetBehavior: BottomSheetBehavior<FrameLayout>) {

        val coordinateTextView = binding.placemarkSetupBs.textView3

        coordinateTextView.setOnClickListener {
            val copyText = coordinateTextView.text.toString().substring(11)
            requireContext().copyToClipboard(copyText)
        }

        bottomSheetBehavior.apply {
            peekHeight = 400
            state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun placeMarkSetUpBottomSheetSetUp(bottomSheetBehavior: BottomSheetBehavior<FrameLayout>) {
        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun mapInitialize(map: Map) {
        val inputTapListener = object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                placeMarkDetailsBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                placeMarkSetUpBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
                setUpPlaceMarkBottomSheet(p1)
                placeMarkSetUpBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        map.apply {
            isNightModeEnabled = true
            addInputListener(inputTapListener)
            move(
                CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0f),
                null
            )
        }
    }

    private fun placemarkInitialize(list: List<RecyclePoint>, imageProvider: ImageProvider) {
        list.forEach { recyclePointItem ->
            val marker = recyclePinsCollection.addPlacemark(
                Point(
                    recyclePointItem.latitude, recyclePointItem.longitude
                ),
                imageProvider
            )
            marker.userData = recyclePointItem
            markerList.add(marker)
        }
    }

    private fun setUpPlaceMarkBottomSheet(point: Point) {

        searchManager.submit(point, 16, SearchOptions(), object : Session.SearchListener {
            override fun onSearchResponse(p0: Response) {
                val data = p0.collection.children.firstOrNull()?.obj
                    ?.metadataContainer
                    ?.getItem(ToponymObjectMetadata::class.java)
                    ?.address
                    ?.components

                val street =
                    data?.firstOrNull { it.kinds.contains(Address.Component.Kind.STREET) }?.name
                val street_no =
                    data?.firstOrNull { it.kinds.contains(Address.Component.Kind.HOUSE) }?.name
                val district =
                    data?.firstOrNull { it.kinds.contains(Address.Component.Kind.DISTRICT) }?.name

                val street_result =
                    if ((street != null) && (street_no != null)) street + " " + street_no
                    else if ((street != null) && (street_no == null)) street
                    else district

                binding.placemarkSetupBs.apply {
                    val coords = point.latitude.toString()
                        .toCoordinateFormat() + " , " + point.longitude.toString()
                        .toCoordinateFormat()
                    textView3.text = "Координаты: " + coords
                    textView.text = street_result
                }
            }

            override fun onSearchError(p0: Error) {
                Toast.makeText(requireContext(), p0.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPlaceMarkBottomSheetDetails(recyclePoint: RecyclePoint) {
        val imageURL = if (recyclePoint.image?.isEmpty() == true) null else recyclePoint.image
        Log.d("123123", (imageURL.toString()))
        with(binding.placemarkInfoBs) {
            textView2.text = recyclePoint.name
            Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.recycle_point_sample_image)
                .error(R.drawable.recycle_point_sample_image)
                .centerCrop()
                .fit()
                .into(imageView5)
            textView3.text = recyclePoint.description

        }
    }

//    fun addMarker(
//        latitude: Double,
//        longitude: Double,
//        @DrawableRes imageRes: Int,
//        userData: Any? = null
//    ): PlacemarkMapObject {
//        val marker = mapObjectCollection.addPlacemark(
//            Point(latitude, longitude),
//            ImageProvider.fromResource(context, imageRes)
//        )
//        marker.userData = userData
//        markerTapListener?.let { marker.addTapListener(it) }
//        return marker
//    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}