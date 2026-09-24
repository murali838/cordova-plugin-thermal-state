# Device Thermal State Plugin

A lightweight Cordova plugin for accessing the thermal status of Android devices.

## Overview

The Device Thermal State Plugin provides access to the Android system thermal status through the `PowerManager` API.

It allows applications to:

- Check whether thermal status information is available.
- Retrieve the current thermal status of the device.
- Identify the current thermal condition using a simple status code and name.

The plugin does not measure device temperature in degrees Celsius. It reports the thermal status provided by the Android operating system.

## Supported Platform

- Android
- Android 10 (API 29) and higher

The thermal status API used by this plugin was introduced in Android 10.

## JavaScript API

The plugin is exposed through:

```javascript
cordova.plugins.thermalState
