package com.jair.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.jair.conf.R
import com.jair.conf.model.Speaker
import com.jair.conf.view.adapter.SpeakerAdapter
import com.jair.conf.view.adapter.SpeakerListener
import com.jair.conf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpeakersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeakersFragment : Fragment() {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()


        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = speakerAdapter
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.listSpeakers.observe(this,Observer<List<Speaker>> { speakers ->
            speakers.let {
                speakerAdapter.updateData(speakers)
            }
            viewModel.isLoading.observe(this,Observer<Boolean> {
                if(it != null)
                    rlBase.visibility = View.INVISIBLE
            } )
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpeakersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpeakersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}