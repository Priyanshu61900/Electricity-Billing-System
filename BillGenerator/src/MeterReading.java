import java.util.Date;

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
