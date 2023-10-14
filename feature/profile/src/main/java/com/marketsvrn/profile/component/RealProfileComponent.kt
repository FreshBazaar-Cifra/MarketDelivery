package com.marketsvrn.profile.component

import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.common.Resource
import com.marketsvrn.data.repository.user.UserRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.inject

class RealProfileComponent(
    componentContext: ComponentContext,
    private val onLogOutAction: () -> Unit
) : ProfileComponent, BaseComponent(componentContext) {
    private val _profile: StateFlow<Resource<User>> = MutableStateFlow(Resource.success(User.getStub()))
    private val repo: UserRepository by inject()
    override fun onLogOut() {
        repo.logOut()
        onLogOutAction()
    }

    override fun onReview() {
    }

    override fun onSupport() {
    }

    override val profile: StateFlow<Resource<User>>
        get() = _profile

}