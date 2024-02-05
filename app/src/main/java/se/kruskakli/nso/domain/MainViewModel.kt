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
import se.kruskakli.nso.data.RetrofitInstance
import se.kruskakli.nso.data.devices.toDeviceUi
import se.kruskakli.nso.data.packages.toPackageUi
import se.kruskakli.nso.domain.DeviceUi
import se.kruskakli.nso.domain.PackageUi

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

    private val _nsoPackages = MutableStateFlow(listOf<PackageUi>())
    val nsoPackages: StateFlow<List<PackageUi>> = _nsoPackages.asStateFlow()

    fun resetNsoPackages() {
        _nsoPackages.value = emptyList()
    }

    fun getNsoPackages() {
        Log.d("MainActivity", "getNsoPackages: ${ipAddress.value}:${port.value}")
        viewModelScope.launch(Dispatchers.IO) {
            val api = RetrofitInstance.getApi(
                "http://${ipAddress.value}:${port.value}/restconf/data/",
                "admin",
                "admin"
            )
            val response = api.getPackages()
            if (response.tailfNcsPackages != null) {
                withContext(Dispatchers.Main) {
                    val newPackages = mutableListOf<PackageUi>()
                    response.tailfNcsPackages.nsoPackages.forEach() {
                        Log.d("MainActivity", "BODY: ${it}")
                        val p = it.toPackageUi()
                        newPackages.add(p)
                    }
                    _nsoPackages.value = newPackages
                }
            }
        }
    }

    private val _nsoDevices = MutableStateFlow(listOf<DeviceUi>())
    val nsoDevices: StateFlow<List<DeviceUi>> = _nsoDevices.asStateFlow()

    fun resetNsoDevices() {
        _nsoDevices.value = emptyList()
    }

    fun getNsoDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            val api = RetrofitInstance.getApi(
                "http://${ipAddress.value}:${port.value}/restconf/data/",
                "admin",
                "admin"
            )
            val response = api.getNsoDevices()
            if (response.nsoDevices != null) {
                withContext(Dispatchers.Main) {
                    val newDevices = mutableListOf<DeviceUi>()
                    response.nsoDevices.devices.forEach() {
                        Log.d("MainActivity", "BODY: ${it}")
                        val p = it.toDeviceUi()
                        newDevices.add(p)
                    }
                    _nsoDevices.value = newDevices
                }
            }
        }
    }
}