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
        return name + " - $" + rate + " per kWh";
    }
}
