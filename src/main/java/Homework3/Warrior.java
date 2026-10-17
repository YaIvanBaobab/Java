package Homework3;

public class Warrior extends Hero {
  private final int armor;

  Warrior(String name, int maxHealth, int baseAttack, int armor) throws Exception {
    super(name, maxHealth, baseAttack);
    checkValue(armor, "Armor");
    this.armor = armor;
  }

  public int getArmor() {
    return armor;
  }

  @Override
  public void takeDamage(int damage) {
    int finalDamage = Math.max(1, damage - getArmor());
    super.takeDamage(finalDamage);
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
    target.takeDamage(getBaseAttack());
    System.out.printf("%s наносит %s удар мечом! \n", getName(), target.getName());
  }

  public boolean canCast() {
    return (getHealth() < 0.5 * getMaxHealth());
  }

  public void castSpecialSkill(Hero target) {
    target.takeDamage(getBaseAttack() + getArmor() * 2);
    System.out.printf("%s наносит %s удар щитом! \n", getName(), target.getName());
  }

  public boolean needsRest() {
    return (getHealth() < 0.15 * getMaxHealth());
  }

  public void rest() {
    heal(getArmor() * 2);
    System.out.printf("Воин %s восстановил себе силы! \n", getName());
  }
}

/*
 castSpecialSkill(Hero target) (Удар щитом): Наносит цели чистый урон, равный getBaseAttack() + (armor * 2). Если на
 момент вызова условия не выполняются, выводит сообщение: "Воин попытался ударить щитом, но потерял равновесие!".

 */