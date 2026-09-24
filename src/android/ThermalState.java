package com.murali.thermalstate;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThermalState extends CordovaPlugin {

    private static final int THERMAL_STATUS_NONE = 0;
    private static final int THERMAL_STATUS_LIGHT = 1;
    private static final int THERMAL_STATUS_MODERATE = 2;
    private static final int THERMAL_STATUS_SEVERE = 3;
    private static final int THERMAL_STATUS_CRITICAL = 4;
    private static final int THERMAL_STATUS_EMERGENCY = 5;
    private static final int THERMAL_STATUS_SHUTDOWN = 6;

    @Override
    public boolean execute(
        String action,
        JSONArray args,
        CallbackContext callbackContext
    ) {

        if ("isAvailable".equals(action)) {

            checkAvailability(callbackContext);
            return true;

        }

        if ("getCurrentThermalState".equals(action)) {

            getCurrentThermalState(callbackContext);
            return true;

        }

        return false;
    }

    private void checkAvailability(
        CallbackContext callbackContext
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            JSONObject result = new JSONObject();

            try {

                result.put("available", true);

                callbackContext.success(result);

            } catch (JSONException e) {

                callbackContext.error(
                    "Failed to create availability response."
                );
            }

        } else {

            callbackContext.error(
                "Thermal status API requires Android 10 (API 29) or higher."
            );
        }
    }

    private void getCurrentThermalState(
        CallbackContext callbackContext
    ) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {

            callbackContext.error(
                "Thermal status API requires Android 10 (API 29) or higher."
            );

            return;
        }

        try {

            PowerManager powerManager =
                (PowerManager) cordova
                    .getActivity()
                    .getSystemService(Context.POWER_SERVICE);

            if (powerManager == null) {

                callbackContext.error(
                    "Unable to access Android PowerManager."
                );

                return;
            }

            int thermalStatus =
                powerManager.getCurrentThermalStatus();

            String thermalState =
                getThermalStateName(thermalStatus);

            JSONObject result = new JSONObject();

            result.put("code", thermalStatus);
            result.put("state", thermalState);

            callbackContext.success(result);

        } catch (Exception e) {

            callbackContext.error(
                e.getMessage() != null
                    ? e.getMessage()
                    : "Failed to get current thermal state."
            );
        }
    }

    private String getThermalStateName(
        int status
    ) {

        switch (status) {

            case THERMAL_STATUS_NONE:
                return "NONE";

            case THERMAL_STATUS_LIGHT:
                return "LIGHT";

            case THERMAL_STATUS_MODERATE:
                return "MODERATE";

            case THERMAL_STATUS_SEVERE:
                return "SEVERE";

            case THERMAL_STATUS_CRITICAL:
                return "CRITICAL";

            case THERMAL_STATUS_EMERGENCY:
                return "EMERGENCY";

            case THERMAL_STATUS_SHUTDOWN:
                return "SHUTDOWN";

            default:
                return "UNKNOWN";
        }
    }
}
