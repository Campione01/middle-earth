package net.jukoz.me.utils;

public final class ClientSideAccess {
    private static final String CLIENT_SCREENS_CLASS = "net.jukoz.me.client.MiddleEarthClientScreens";

    private ClientSideAccess() {
    }

    public static void openMiddleEarthMap() {
        invokeClientMethod("openMiddleEarthMap");
    }

    public static boolean hasShiftDown() {
        return invokeClientBoolean("hasShiftDown");
    }

    public static boolean hasAltDown() {
        return invokeClientBoolean("hasAltDown");
    }

    private static void invokeClientMethod(String methodName) {
        try {
            Class.forName(CLIENT_SCREENS_CLASS).getMethod(methodName).invoke(null);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Failed to invoke client method " + methodName, e);
        }
    }

    private static boolean invokeClientBoolean(String methodName) {
        try {
            Object value = Class.forName(CLIENT_SCREENS_CLASS).getMethod(methodName).invoke(null);
            return value instanceof Boolean result && result;
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }
}
