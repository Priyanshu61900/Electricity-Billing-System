import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<MeterReading> meterReadings;
    private Tariff tariff;

    public Customer(String name, Tariff tariff) {
        this.name = name;
        this.tariff = tariff;
        this.meterReadings = new ArrayList<>();
    }

    public void addMeterReading(MeterReading reading) {
        meterReadings.add(reading);
    }

    public double calculateTotalBill() {
        double totalBill = 0.0;
        for (MeterReading reading : meterReadings) {
            totalBill += reading.getUsage() * tariff.getRate();
        }
        return totalBill;
    }

    @Override
    public String toString() {
        return "Customer: " + name + "\n" +
               "Tariff: " + tariff + "\n" +
               "Meter Readings: " + meterReadings.size() + "\n" +
               "Total Bill: $" + calculateTotalBill();
    }
}
