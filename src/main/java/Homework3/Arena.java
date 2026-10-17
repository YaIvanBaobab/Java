package Homework3;

import java.util.Collection;
import java.util.Collections;

public class Arena {
  private final Hero fighter1, fighter2;
  private int roundCounter;

  Arena(Hero fighter1, Hero fighter2) {
    this.fighter1 = fighter1;
    this.fighter2 = fighter2;
  }

  boolean oneMotion(Hero attacker, Hero defender) {
    ActionType action = attacker.makeTurn(defender);
    System.out.printf("Игрок %s совершил действие: %s\n", attacker.getName(), action.getMeaning());

    if (!defender.isAlive()) {
      System.out.printf("Игрок %s погиб.\n", defender.getName());
      System.out.println();
      System.out.printf("Игрок %s объявляется победителем!", attacker.getName());
      return false;
    }
    return true;
  }

  void startTournament() {
    while (fighter1.isAlive() && fighter2.isAlive()) {
      roundCounter++;
      System.out.println();
      System.out.printf("РАУНД НОМЕР %d!\n", roundCounter);
      boolean rand = Math.random() > 0.5;

      Hero attacker = fighter1;
      Hero defender = fighter2;

      if (rand) {
        attacker = fighter2;
        defender = fighter1;
      }
      if (oneMotion(attacker, defender)) {
        if (!oneMotion(defender, attacker)) {
          break;
        }
      } else {
        break;
      }

      fighter1.status();
      System.out.println();
      fighter2.status();
      System.out.println();
    }
  }
}
