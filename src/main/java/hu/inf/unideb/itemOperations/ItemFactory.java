package hu.inf.unideb.itemOperations;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import hu.inf.unideb.Items.Armor;
import hu.inf.unideb.Items.Item;
import hu.inf.unideb.Items.Weapon;
import hu.inf.unideb.Items.WeaponType;

/**
 * The ItemFactory class is responsible for instantiating item objects.
 *
 * @author Erik Kakócz
 *
 */
public class ItemFactory {

    /**
     * The number of the first child item of a node.
     */
    private static final int FIRSTITEMCHILD = 1;

    /**
     * The number of the second child item of a node.
     */
    private static final int SECONDITEMCHILD = 3;

    /**
     * The number of the third child item of a node.
     */
    private static final int THIRDITEMCHILD = 5;

    /**
     * The number of the fourth child item of a node.
     */
    private static final int FOURTHITEMCHILD = 7;

    /**
     * The number of the fifth child item of a node.
     */
    private static final int FIFTHITEMCHILD = 9;

    /**
     * Logger used for logging.
     */
    private static Logger logger;

    /**
     * public constructor for ItemFactory class.
     */
    public ItemFactory() {
        logger = LoggerFactory.getLogger(ItemFactory.class);
    }

    /**
     * Gets a NodeList from one of the xml documents in the resources folder
     * depending on which is needed.
     * @param num The number of the document.
     * @return A NodeList containing the children of the root element of the 
     * xml document specified by num.
     */
    private NodeList getNodesFromDocument(int num) {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        NodeList nodes=null;
        try {
            builder = dFactory.newDocumentBuilder();
            ClassLoader classLoader = this.getClass().getClassLoader();
            Document doc;
            switch(num) {
                case 0:doc=builder.parse(classLoader
                        .getResourceAsStream("Items.xml"));
                       nodes=doc.getElementsByTagName("item");
                       break;
                case 1:doc=builder.parse(classLoader
                        .getResourceAsStream("weapons.xml"));
                       nodes=doc.getElementsByTagName("weapon");
                       break;
                case 2:doc=builder.parse(classLoader
                        .getResourceAsStream("armors.xml"));
                       nodes=doc.getElementsByTagName("armor");
                       break;
                case 3:doc=builder.parse(classLoader
                        .getResourceAsStream("recipes.xml"));
                       nodes=doc.getElementsByTagName("recipe");
                       break;
            }
        } catch (SAXException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
        return nodes;
    }

    /**
     * Gets a crafting recipe from the "recipes.xml" document.
     * @param id the id of the object that can be crafted according to
     * the recipe.
     * @return A Recipe object.
     */
    public Recipe getCraftingRecipeById(final int id) {
        Recipe result = new Recipe();
        NodeList nodes=getNodesFromDocument(3);
        for(int i=0;i<nodes.getLength();i++) {
            Element element=(Element)nodes.item(i);
            NodeList cn=element.getChildNodes();
            if(Integer.parseInt((cn.item(THIRDITEMCHILD)
                    .getFirstChild().getNodeValue()))==id){
                result.setIngredientOneId(Integer.parseInt(
                        (cn.item(FIRSTITEMCHILD).getFirstChild()
                         .getNodeValue())));
                result.setIngredientTwoId(Integer.parseInt(
                        (cn.item(SECONDITEMCHILD).getFirstChild()
                         .getNodeValue())));
                result.setItemId(Integer.parseInt(
                        (cn.item(THIRDITEMCHILD).getFirstChild()
                         .getNodeValue())));
                result.setAmount(Integer.parseInt(
                        (cn.item(FOURTHITEMCHILD).getFirstChild()
                         .getNodeValue())));
            }
        }
        //logger.debug(result.toString());
        return result;
    }

    /**
     * Looks up the name of an item in the xml documents in the resources 
     * folder.
     * @param id The id of the item.
     * @return The name of the item.
     */
    public String getItemNameById(int id) {
        NodeList nodes=null;
        for(int i=0;i<3;i++) {
            nodes=getNodesFromDocument(i);
            for(int j=0;j<nodes.getLength();j++) {
                Element e=(Element)nodes.item(j);
                if(Integer.parseInt(e.getAttribute("id"))==id) {
                    return e.getChildNodes().item(SECONDITEMCHILD)
                            .getFirstChild().getNodeValue();
                }
            }
        }
        return null;
    }

    /**
     * Returns the crafting recipes from the "recipes.xml" file in the
     * resources folder. 
     * @return An ArrayList containing all the crafting recipes.
     */
    public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> recipes=new ArrayList<Recipe>();
        NodeList nodes = getNodesFromDocument(3);
        for(int i=0;i<nodes.getLength();i++) {
            Element element=(Element)nodes.item(i);
            NodeList cn = element.getChildNodes();
            Recipe recipe=new Recipe(
            Integer.parseInt((cn.item(FIRSTITEMCHILD).getFirstChild().getNodeValue())),
            Integer.parseInt((cn.item(SECONDITEMCHILD).getFirstChild().getNodeValue())),
            Integer.parseInt((cn.item(THIRDITEMCHILD).getFirstChild().getNodeValue())),
            Integer.parseInt((cn.item(FOURTHITEMCHILD).getFirstChild().getNodeValue())));
            //logger.debug(recipe.toString());
            recipes.add(recipe);
        }
        return recipes;
    }

    /**
     * Instantiates an item using the xml document containing the attributes of
     * the items.
     *
     * @param id The unique id of the item to instantiate.
     * @return The instantiated item.
     */
    public final Item instantiateItem(final int id) {
        Item item = null;
        NodeList items = getNodesFromDocument(0);
            for (int i = 0; i < items.getLength(); i++) {
                Element element = (Element) items.item(i);
                if (Integer.parseInt((element).getAttribute("id")) == id) {
                    int iId = Integer.parseInt(element.getAttribute("id"));
                    String w = element.getChildNodes().item(FIRSTITEMCHILD).
                            getFirstChild().getNodeValue();
                    item = new Item(iId, Double.parseDouble(w),
                                    Item.MAXIMUM_DURABILITY,
                                    element.getChildNodes().
                                    item(SECONDITEMCHILD).getFirstChild().
                                    getNodeValue());
                }
            }
        return item;
    }

    /**
     * Takes a string argument and returns the {@link WeaponType} the string
     * value represents.
     *
     * @param s A string representing one of the weapon types.
     * @return A WeaponType enum.
     */
    private WeaponType getType(final String s) {
        if (s.equals("LONGSWORD")) {
            return WeaponType.LONGSWORD;
        } else if (s.equals("SWORD")) {
            return WeaponType.SWORD;
        } else if (s.equals("DAGGER")) {
            return WeaponType.DAGGER;
        } else if (s.equals("STAFF")) {
            return WeaponType.STAFF;
        } else if (s.equals("BOW")) {
            return WeaponType.BOW;
        } else if (s.equals("CROSSBOW")) {
            return WeaponType.CROSSBOW;
        } else {
            return null;
        }
    }

    /**
     * Instantiates a {@link Weapon} object. This method is used by the
     * {@link ItemTinkering} class.
     *
     * @param id The id of the weapon to instantiate.
     * @param durability The durability of the weapon to instantiate.
     * @return A {@link Weapon} object
     */
    public final Weapon instantiateWeapon(final int id) {
        Weapon weapon = null;
        NodeList items = getNodesFromDocument(1);
        for (int i = 0; i < items.getLength(); i++) {
            Element element = (Element) items.item(i);
            if (Integer.parseInt((element).getAttribute("id")) == id) {
                int iId = Integer.parseInt(element.getAttribute("id"));
                NodeList n = element.getChildNodes();
                String w = n.item(FIRSTITEMCHILD).getFirstChild().
                            getNodeValue();
                String name = n.item(SECONDITEMCHILD).getFirstChild().
                            getNodeValue();
                double attack = Double.parseDouble(n.item(THIRDITEMCHILD).
                                getFirstChild().getNodeValue());
                double speed = Double.parseDouble(n.item(FOURTHITEMCHILD).
                                getFirstChild().getNodeValue());
                WeaponType type = getType(n.item(FIFTHITEMCHILD).
                                getFirstChild().getNodeValue());
                weapon = new Weapon(iId, Double.parseDouble(w),
                                    100, name, attack, speed, type);
            }
        }
        
        return weapon;
    }

    public final Armor instantiateArmor(final int id) {
        Armor armor = null;
        NodeList items = getNodesFromDocument(2);
        for (int i = 0; i < items.getLength(); i++) {
            Element element = (Element) items.item(i);
            if (Integer.parseInt((element).getAttribute("id")) == id) {
                int iId = Integer.parseInt(element.getAttribute("id"));
                NodeList n = element.getChildNodes();
                String w = n.item(FIRSTITEMCHILD).getFirstChild().
                            getNodeValue();
                String name = n.item(SECONDITEMCHILD).getFirstChild().
                            getNodeValue();
                int damageRed = Integer.parseInt(n.item(THIRDITEMCHILD).
                                getFirstChild().getNodeValue());
                int movImp = Integer.parseInt(n.item(FOURTHITEMCHILD).
                                getFirstChild().getNodeValue());
                armor = new Armor(iId, Double.parseDouble(w),
                                  100, name, damageRed, movImp);
            }
        }
        return armor;
    }

    public final Item create(final int id) {
        Item item=instantiateItem(id);
        if(item==null) {
            item=instantiateWeapon(id);
        }
        if(item==null) {
            item=instantiateArmor(id);
        }
        return item;
    }
}
