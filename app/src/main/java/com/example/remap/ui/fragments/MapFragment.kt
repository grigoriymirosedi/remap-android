package com.example.remap.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remap.R
import com.example.remap.common.getBitmapFromVectorDrawable
import com.example.remap.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    private lateinit var mapView: MapView
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

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
        val bitmap = requireContext().getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

        map.isNightModeEnabled = true

        map.move(
            CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        map.mapObjects.addPlacemark(
            Point(47.226873, 39.593675),
            ImageProvider.fromBitmap(bitmap))
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