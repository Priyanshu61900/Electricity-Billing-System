import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Tariff {
    private String name;
    private double rate;

    public Tariff(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name + " - ₹" + rate + " per kWh";
    }
}

class MeterReading {
    private Date readingDate;
    private double usage;

    public MeterReading(Date readingDate, double usage) {
        this.readingDate = readingDate;
        this.usage = usage;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public double getUsage() {
        return usage;
    }
}

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
               "Total Bill: ₹" + calculateTotalBill();
    }
}

public class ElectricityBillGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Select tariff (Residential/Commercial): ");
        String tariffType = scanner.nextLine();

        Tariff selectedTariff;
        if (tariffType.equalsIgnoreCase("Residential")) {
            selectedTariff = new Tariff("Residential", 0.15);
        } else if (tariffType.equalsIgnoreCase("Commercial")) {
            selectedTariff = new Tariff("Commercial", 0.20);
        } else {
            System.out.println("Invalid tariff type. Using default Residential tariff.");
            selectedTariff = new Tariff("Residential", 0.15);
        }

        Customer customer = new Customer(customerName, selectedTariff);

        boolean addMoreReadings = true;
        while (addMoreReadings) {
            Date readingDate = null;
            boolean validDate = false;
            while (!validDate) {
                System.out.print("Enter reading date (YYYY-MM-DD): ");
                String readingDateStr = scanner.nextLine();
                try {
                    readingDate = parseDate(readingDateStr);
                    validDate = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter again.");
                }
            }

            double usage = -1;
            boolean validUsage = false;
            while (!validUsage) {
                System.out.print("Enter usage in kWh: ");
                if (scanner.hasNextDouble()) {
                    usage = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    if (usage >= 0) {
                        validUsage = true;
                    } else {
                        System.out.println("Usage cannot be negative. Please enter again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            customer.addMeterReading(new MeterReading(readingDate, usage));

            System.out.print("Add more readings? (yes/no): ");
            String moreReadingsInput = scanner.nextLine();
            addMoreReadings = moreReadingsInput.equalsIgnoreCase("yes");
        }

        scanner.close();

        System.out.println("\n" + customer);
    }

    private static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return dateFormat.parse(dateStr);
    }
}
