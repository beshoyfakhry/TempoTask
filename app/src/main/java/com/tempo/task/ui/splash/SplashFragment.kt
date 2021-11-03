package com.tempo.task.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.tempo.task.R
import com.tempo.task.utils.Extensions.showToast
import com.tempo.task.utils.Utils
import java.util.*
import kotlin.concurrent.schedule


class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utils.isOnline(requireContext())) {

            Timer().schedule(2000) {

                activity?.runOnUiThread {
                    val action = SplashFragmentDirections.actionSplashFragmentToNewsListing()
                    requireView().findNavController().navigate(action)
                }

            }
        } else {
            requireContext().showToast(getString(R.string.no_internet_connection))
            requireActivity().finish()
        }
    }
}