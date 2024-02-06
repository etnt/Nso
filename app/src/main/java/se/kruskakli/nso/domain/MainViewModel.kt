package se.kruskakli.nso.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.kruskakli.nso.data.NsoApi
import se.kruskakli.nso.data.RetrofitInstance
import se.kruskakli.nso.data.alarms.toAlarmUi
import se.kruskakli.nso.data.devices.toDeviceUi
import se.kruskakli.nso.data.packages.toPackageUi

/*
In this ViewModel, I've replaced remember { mutableStateOf(...) } with
MutableStateFlow(...), and I've moved your functions into the ViewModel
as methods. I've also replaced GlobalScope.launch with viewModelScope.launch
to tie the coroutine's lifecycle to the ViewModel's lifecycle.
*/
class MainViewModel : ViewModel() {
    private val _name = MutableStateFlow("Blueberry")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _ipAddress = MutableStateFlow("10.147.40.166")
    val ipAddress: StateFlow<String> = _ipAddress.asStateFlow()

    private val _port = MutableStateFlow("8080")
    val port: StateFlow<String> = _port.asStateFlow()

    fun applySettings(newName: String, newIp: String, newPort: String) {
        _name.value = newName
        _ipAddress.value = newIp
        _port.value = newPort
    }

    private val _refresh = MutableStateFlow(true)
    val refresh: StateFlow<Boolean> = _refresh.asStateFlow()

    fun setRefresh(newRefresh: Boolean) {
        _refresh.value = newRefresh
    }

    private val _apiError = MutableStateFlow<String?>(null)
    val apiError: StateFlow<String?> = _apiError.asStateFlow()
    fun clearApiError() {
        _apiError.value = null
    }

    private val _nsoPackages = MutableStateFlow(listOf<PackageUi>())
    val nsoPackages: StateFlow<List<PackageUi>> = _nsoPackages.asStateFlow()

    fun resetNsoPackages() {
        _nsoPackages.value = emptyList()
    }

    private val _nsoDevices = MutableStateFlow(listOf<DeviceUi>())
    val nsoDevices: StateFlow<List<DeviceUi>> = _nsoDevices.asStateFlow()

    fun resetNsoDevices() {
        _nsoDevices.value = emptyList()
    }

    private val _nsoAlarms = MutableStateFlow(listOf<AlarmUi>())
    val nsoAlarms: StateFlow<List<AlarmUi>> = _nsoAlarms.asStateFlow()

    fun resetNsoAlarms() {
        _nsoAlarms.value = emptyList()
    }

    /*
        In this code, performApiCall now creates the NsoApi instance and
        passes it to apiCall. The apiCall function now takes an NsoApi
        parameter, which it uses to make the API call. This way, the
        creation of the NsoApi instance is shared between getNsoPackages
        and getNsoDevices, reducing code duplication.
     */
    private suspend fun <T> performApiCall(
        apiCall: suspend (api: NsoApi) -> T,
        onSuccess: (T) -> Unit
    ) {
        try {
            val api = RetrofitInstance.getApi(
                "http://${ipAddress.value}:${port.value}/restconf/data/",
                "admin",
                "admin"
            )
            val response = apiCall(api)
            withContext(Dispatchers.Main) {
                onSuccess(response)
            }
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error: ${e.message}")
            _apiError.value = e.message
        }
    }
    
    fun getNsoPackages() {
        viewModelScope.launch(Dispatchers.IO) {
            performApiCall(
                apiCall = { api -> api.getPackages() },
                onSuccess = { response ->
                    if (response.tailfNcsPackages != null) {
                        val newPackages = mutableListOf<PackageUi>()
                        response.tailfNcsPackages.nsoPackages.forEach() {
                            Log.d("MainActivity", "getNsoPackages BODY: ${it}")
                            val p = it.toPackageUi()
                            newPackages.add(p)
                        }
                        _nsoPackages.value = newPackages
                    }
                }
            )
        }
    }
    
    fun getNsoDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            performApiCall(
                apiCall = { api -> api.getNsoDevices() },
                onSuccess = { response ->
                    if (response.nsoDevices != null) {
                        val newDevices = mutableListOf<DeviceUi>()
                        response.nsoDevices.devices.forEach() {
                            Log.d("MainActivity", "getNsoDevices BODY: ${it}")
                            val p = it.toDeviceUi()
                            newDevices.add(p)
                        }
                        _nsoDevices.value = newDevices
                    }
                }
            )
        }
    }

    fun getNsoAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            performApiCall(
                apiCall = { api -> api.getNsoAlarmList() },
                onSuccess = { response ->
                    if (response.nsoAlarmList != null) {
                        val newAlarms = mutableListOf<AlarmUi>()
                        response.nsoAlarmList.alarm.forEach() {
                            Log.d("MainActivity", "getNsoDevices BODY: ${it}")
                            val p = it.toAlarmUi()
                            newAlarms.add(p)
                        }
                        _nsoAlarms.value = newAlarms
                    }
                }
            )
        }
    }

}
