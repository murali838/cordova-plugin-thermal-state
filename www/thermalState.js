var exec = require("cordova/exec");

var ThermalState = function () {};

/**
 * Checks whether the Android device supports
 * the thermal status API required by this plugin.
 *
 * Success callback:
 * {
 *     available: true
 * }
 *
 * Error callback:
 * error message
 */
ThermalState.prototype.isAvailable = function (
    successCallback,
    errorCallback
) {

    exec(
        successCallback,
        errorCallback,
        "ThermalState",
        "isAvailable",
        []
    );
};

/**
 * Retrieves the current device thermal status.
 *
 * Success callback:
 * {
 *     code: 0,
 *     state: "NONE"
 * }
 *
 * Error callback:
 * error message
 */
ThermalState.prototype.getCurrentThermalState = function (
    successCallback,
    errorCallback
) {

    exec(
        successCallback,
        errorCallback,
        "ThermalState",
        "getCurrentThermalState",
        []
    );
};

module.exports = new ThermalState();
