/**
 * Objects implementing this interface notify listeners when hit.
 */
public interface HitNotifier {
    /**
     * Adds the given hit listener to this hit notifier's notifying list.
     * @param hl A HitListener to add to this hit notifier's notifying list
     */
    void addHitListener(HitListener hl);
}
