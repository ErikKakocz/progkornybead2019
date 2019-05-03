package hu.inf.unideb.itemOperations;

import java.io.IOException;

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
     * DocumentBuilder object used to get a jaxp documentbuilder instance.
     */
    private DocumentBuilderFactory dFactory;

    /**
     * A jaxp DocumentBuilder instance used to parse the xml.
     */
    private DocumentBuilder builder;

    /**
     * Document object representing the xml document from which itemFactory
     * class instantiates the items.
     */
    private Document doc;

    /**
     * Logger used for logging.
     */
    private static Logger logger = LoggerFactory.getLogger(ItemFactory.class);

    /**
     * public constructor for ItemFactory class.
     */
    public ItemFactory() {
        dFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = dFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Instantiates an item using the xml document containing the attributes of
     * the items.
     *
     * @param id The unique id of the item to instantiate.
     * @return The instantiated item.
     */
    public final Item instantiateItem(final int id) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Item item = null;
        try {
            doc = builder.parse(classLoader.getResourceAsStream("Items.xml"));
            NodeList items = doc.getElementsByTagName("item");
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
        } catch (SAXException e) {
            logger.error("InstantiateItem method threw SAXException.");
        } catch (IOException e) {
            logger.error("InstantiateItem method threw IOException.");
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
    public final Weapon instantiateWeapon(final int id, final int durability) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Weapon weapon = null;
        try {
            doc = builder.parse(classLoader.getResourceAsStream("Weapons.xml"));
            NodeList items = doc.getElementsByTagName("weapon");
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
                                        durability, name, attack, speed, type);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return weapon;
    }

    public final Armor instantiateArmor(final int id, final int durability) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Armor armor = null;
        try {
            doc = builder.parse(classLoader.getResourceAsStream("Weapons.xml"));
            NodeList items = doc.getElementsByTagName("weapon");
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
                                      durability, name, damageRed, movImp);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return armor;
    }
}
