
/**
 * Description
 *
 * @author Alexander DeMello
 * @version program 4
 */
public interface BSTTranslator<E extends Comparable<? super E>>
{

   public BST<E> getBST();

   public String translate(String s);

}
