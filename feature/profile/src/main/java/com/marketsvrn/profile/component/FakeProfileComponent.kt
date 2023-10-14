package com.marketsvrn.profile.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProfileComponent: ProfileComponent {
    override fun onLogOut() {
    }

    override fun onReview() {
    }

    override fun onSupport() {
    }

    override val profile: StateFlow<Resource<User>>
        get() = MutableStateFlow(Resource.success(User.getStub()))
}