/**
 * This interface serves as a contract to the user that if a class implements it, the class should
 * be able to inflict other "classes" (i.e. players).
 */
public interface CauseDamage {
    double damage(double ability);
}
