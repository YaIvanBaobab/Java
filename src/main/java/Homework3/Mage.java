package Homework3;

public class Mage extends Hero {
  private final int maxMana;
  private int mana;

  Mage(String name, int maxHealth, int baseAttack, int maxMana) throws Exception {
    super(name, maxHealth, baseAttack);
    checkValue(maxMana, "Mana limit");
    this.mana = maxMana;
    this.maxMana = maxMana;
  }

  public int getMana() {
    return mana;
  }

  public int getMaxMana() {
    return maxMana;
  }

  @Override
  public ActionType makeTurn(Hero target) {
    if (canCast()) {
      castSpecialSkill(target);
      return ActionType.SPECIAL_SKILL;
    } else if (needsRest()) {
      rest();
      return ActionType.REST;
    } else {
      attack(target);
      return ActionType.BASE_ATTACK;
    }
  }

  @Override
  public void attack(Hero target) {
    int damage = 0;
    if (mana > 10) {
      restoreMana(-10);
      target.takeDamage(getBaseAttack() * 2);
      System.out.printf("%s совершает Магический выстрел по %s!\n", getName(), target.getName());
    } else {
      target.takeDamage(getBaseAttack() / 2);
      restoreMana(5);
      System.out.printf("%s сокрушительно бьет %s по голове!\n", getName(), target.getName());
    }
  }

  @Override
  public void status(){
    super.status();
    System.out.printf("Осталось маны: %d из %d\n", getMana(), getMaxMana());
  }

  public void restoreMana(int delta) {
    if (delta > 0) {
      mana = Math.max(0, mana + delta);
    } else {
      mana = Math.min(maxMana, mana + delta);
    }
  }

  public boolean canCast() {
    return (getMana() >= 25);
  }

  public void castSpecialSkill(Hero target) {
    target.takeDamage(getBaseAttack() * 3);
    restoreMana(-25);
    System.out.printf("%s наносит %s удар щитом!\n", getName(), target.getName());
  }

  public boolean needsRest() {
    return (getMana() == 0 || getHealth() < 0.30 * getMaxHealth());
  }

  public void rest() {
    restoreMana(getMaxMana());
    heal(getBaseAttack());
    System.out.printf("Маг %s восстановил себе силы!\n", getName());
  }
}