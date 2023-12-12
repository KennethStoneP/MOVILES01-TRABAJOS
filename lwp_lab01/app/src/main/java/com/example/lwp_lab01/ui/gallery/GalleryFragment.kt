package com.example.lwp_lab01.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lwp_lab01.databinding.FragmentGalleryBinding
import com.example.lwp_lab01.R

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var text_gallery = root.findViewById<TextView>(R.id.text_gallery)
        text_gallery.setText(R.string.message_about_us)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}