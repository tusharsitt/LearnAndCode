public class ATMDeviceController {

    public void withdraw(String accountId, double amount) throws DeviceNotFoundException, DeviceSuspendedException,
            NetworkConnectionException, InsufficientFundsException {
        DeviceHandle handle = getValidHandle();
        DeviceRecord record = getActiveDeviceRecord(handle);

        ensureWifiConnected(record);
        ensureSufficientBalance(accountId, amount);

        dispenseCash(handle, amount);
    }

    private DeviceHandle getValidHandle() throws DeviceNotFoundException {
        DeviceHandle handle = getHandle(DEV1);

        if (handle == DeviceHandle.INVALID) {
            throw new DeviceNotFoundException();
        }

        return handle;
    }

    private DeviceRecord getActiveDeviceRecord(DeviceHandle handle) throws DeviceSuspendedException {
        DeviceRecord record = retrieveDeviceRecord(handle);

        if (record.getStatus() == DEVICE_SUSPENDED) {
            throw new DeviceSuspendedException();
        }

        return record;
    }

    private void ensureWifiConnected(DeviceRecord record) throws NetworkConnectionException {
        if (record.getWifiConnection() != WIFI_CONNECTED) {
            throw new NetworkConnectionException();
        }
    }

    private void ensureSufficientBalance(String accountId, double amount) throws InsufficientFundsException {
        if (getBalance(accountId) < amount) {
            throw new InsufficientFundsException();
        }
    }
}