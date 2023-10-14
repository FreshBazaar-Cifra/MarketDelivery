package com.marketsvrn.profile.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.User
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {
    fun onLogOut()
    fun onReview()
    fun onSupport()

    val profile: StateFlow<Resource<User>>
}