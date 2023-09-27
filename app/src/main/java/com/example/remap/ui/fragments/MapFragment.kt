package com.example.remap.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.remap.R
import com.example.remap.core.RemapClient
import com.example.remap.core.util.Resource
import com.example.remap.core.util.getBitmapFromVectorDrawable
import com.example.remap.data.repository.RecyclePointRepositoryImpl
import com.example.remap.databinding.FragmentMapBinding
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.ui.viewmodels.MapFragmentViewModel
import com.example.remap.ui.viewmodels.ViewModelProviderFactory
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    private lateinit var viewModel: MapFragmentViewModel
    private lateinit var mapView: MapView
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!


    private var recyclePointList: List<RecyclePoint> = listOf()
    private lateinit var recyclePinsCollection: MapObjectCollection

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
        map.isNightModeEnabled = true

        val bitmap = requireContext().getBitmapFromVectorDrawable(R.drawable.ic_map_pin)
        val imageProvider = ImageProvider.fromBitmap(bitmap)
        recyclePinsCollection = map.mapObjects.addCollection()

        val repository = RecyclePointRepositoryImpl(RemapClient.provideRemapRecycleAPI())
        val viewModelProviderFactory = ViewModelProviderFactory(repository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MapFragmentViewModel::class.java)
        viewModel.recyclePointResponse.observe(viewLifecycleOwner, Observer {   recyclePointResponse ->
            when(recyclePointResponse) {
                is Resource.Success -> {
                    recyclePointResponse.data?.let {
                        recyclePointList = it
                        mapInitialize(recyclePointList, imageProvider)
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Data is loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), recyclePointResponse.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        map.move(
            CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )


    }

    fun mapInitialize(list: List<RecyclePoint>, imageProvider: ImageProvider) {
        list.forEach { recyclePointItem ->
            recyclePinsCollection.addPlacemark(Point(
                recyclePointItem.latitude, recyclePointItem.longitude),
                imageProvider)
        }
    }

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