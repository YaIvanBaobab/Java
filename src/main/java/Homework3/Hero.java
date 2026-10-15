package Homework3;

public abstract class Hero implements Castable, Restable {
  final int MIN_STAT_VALUE = 1;

  private final String name;
  private final int maxHealth;
  private final int baseAttack;
  private int health;

  public Hero(String name, int maxHealth, int baseAttack) throws Exception {
    checkValue(maxHealth, "maxHealth");
    checkValue(baseAttack, "baseAttack");

    this.name = name;
    this.maxHealth = maxHealth;
    this.health = maxHealth;
    this.baseAttack = baseAttack;
  }

  protected void checkValue(int value, String valueName) throws Exception {
    if (value < MIN_STAT_VALUE) {
      throw new Exception(String.format("Invalid value: \"%s\"", valueName));
    }
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public int getBaseAttack() {
    return baseAttack;
  }

  public int getHealth() {
    return health;
  }

  public String getName() {
    return name;
  }

  public abstract void attack(Hero target);

  public abstract ActionType makeTurn(Hero target);

  public void takeDamage(int damage) {
    health -= damage;
    if (health <= 0) {
      health = 0;
      //System.out.println(name + " пал в бою!");
    }
  }

  public void heal(int amount) {
    health = Math.max(health + amount, maxHealth);
  }

  public boolean isAlive() {
    return health > 0;
  }

  public void status() {
    System.out.println();
    System.out.printf("ГЕРОЙ %s\n", getName());
    System.out.printf("Осталось здоровья: %d из %d", getHealth(), getMaxHealth());
  }
}
