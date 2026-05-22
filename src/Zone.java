public class Zone {
   private int level;
   private int population;
   private int goods;
   private int lifestyle;
   private double electricity;
   private double water;
   private double internet;
   // Service booleans
  private boolean hasUtilities;
  private boolean hasSecurity;
  private boolean hasHealth;
  private boolean hasEducation;

  public Zone(int level, int population, int goods, int lifestyle, double electricity, double water, double internet, boolean hasUtilities, boolean hasSecurity, boolean hasHealth, boolean hasEducation) {
    this.level = level;
    this.population = population;
    this.goods = goods;
    this.lifestyle = lifestyle;
    this.electricity = electricity;
    this.water = water;
    this.internet = internet;
    this.hasUtilities = hasUtilities;
    this.hasSecurity = hasSecurity;
    this.hasHealth = hasHealth;
    this.hasEducation = hasEducation;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public int getGoods() {
    return goods;
  }

  public void setGoods(int goods) {
    this.goods = goods;
  }

  public int getLifestyle() {
    return lifestyle;
  }

  public void setLifestyle(int lifestyle) {
    this.lifestyle = lifestyle;
  }

  public double getElectricity() {
    return electricity;
  }

  public void setElectricity(double electricity) {
    this.electricity = electricity;
  }

  public double getWater() {
    return water;
  }

  public void setWater(double water) {
    this.water = water;
  }

  public double getInternet() {
    return internet;
  }

  public void setInternet(double internet) {
    this.internet = internet;
  }

  public boolean isHasUtilities() {
    return hasUtilities;
  }

  public void setHasUtilities(boolean hasUtilities) {
    this.hasUtilities = hasUtilities;
  }

  public boolean isHasSecurity() {
    return hasSecurity;
  }

  public void setHasSecurity(boolean hasSecurity) {
    this.hasSecurity = hasSecurity;
  }

  public boolean isHasHealth() {
    return hasHealth;
  }

  public void setHasHealth(boolean hasHealth) {
    this.hasHealth = hasHealth;
  }

  public boolean isHasEducation() {
    return hasEducation;
  }

  public void setHasEducation(boolean hasEducation) {
    this.hasEducation = hasEducation;
  }
}
  class Housing extends Zone{
    public Housing(int level, int population, int goods, int lifestyle, double electricity, double water, double internet, boolean hasUtilities, boolean hasSecurity, boolean hasHealth, boolean hasEducation) {
      super(level, population, goods, lifestyle, electricity, water, internet, hasUtilities, hasSecurity, hasHealth, hasEducation);
     }
  }
  class Industrial extends Zone{
    public Industrial(int level, int population, int goods, int lifestyle, double electricity, double water, double internet, boolean hasUtilities, boolean hasSecurity, boolean hasHealth, boolean hasEducation) {
      super(level, population, goods, lifestyle, electricity, water, internet, hasUtilities, hasSecurity, hasHealth, hasEducation);
    }
  }
class Commercial extends Zone {
  public Commercial(int level, int population, int goods, int lifestyle, double electricity, double water, double internet, boolean hasUtilities, boolean hasSecurity, boolean hasHealth, boolean hasEducation) {
    super(level, population, goods, lifestyle, electricity, water, internet, hasUtilities, hasSecurity, hasHealth, hasEducation);
  }
}
