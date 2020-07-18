package com.heathkev.quizado.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.heathkev.quizado.R
import com.heathkev.quizado.data.User
import com.heathkev.quizado.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = if(firebaseAuth.currentUser != null){
            val authUser = firebaseAuth.currentUser!!
            User(
                authUser.uid,
                authUser.displayName,
                authUser.photoUrl,
                authUser.email
            )
        }else{
            User()
        }

        val viewModelFactory = ProfileViewModelFactory(currentUser)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}