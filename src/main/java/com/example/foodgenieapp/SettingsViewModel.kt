package com.example.foodgenieapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val localizationManager: LocalizationManager,
    private val notificationService: NotificationService
) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser.asStateFlow()

    private val _languages = MutableStateFlow<List<Language>>(emptyList())
    val languages: StateFlow<List<Language>> = _languages.asStateFlow()

    private val _currentLanguage = MutableStateFlow(localizationManager.getCurrentLanguage())
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    init {
        loadLanguages()
    }

    fun setCurrentUser(user: UserEntity) {
        _currentUser.value = user
        _currentLanguage.value = user.language
    }

    fun changeLanguage(languageCode: String) {
        viewModelScope.launch {
            _currentLanguage.value = languageCode
            localizationManager.setAppLanguage(languageCode)

            _currentUser.value?.let { user ->
                val updatedUser = user.copy(language = languageCode)
                userRepository.updateUser(updatedUser)
                _currentUser.value = updatedUser
            }
        }
    }

    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch {
            _currentUser.value?.let { user ->
                val updatedUser = user.copy(notificationsEnabled = enabled)
                userRepository.updateUser(updatedUser)
                _currentUser.value = updatedUser

                if (enabled) {
                    // Test notification
                    notificationService.showPromotionalNotification(
                        "Notifications Enabled",
                        "You'll now receive order updates and promotions"
                    )
                }
            }
        }
    }

    private fun loadLanguages() {
        _languages.value = localizationManager.getSupportedLanguages()
    }
}