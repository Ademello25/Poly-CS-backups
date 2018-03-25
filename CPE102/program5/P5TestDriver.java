/*
 * Test driver for Dictionary class (without encryption).
 * @author Kurt Mammen
 * @version 04/20/2012 - 1) Added tests for new add method
 *                       2) Added tests for new default constructor
 *                       3) Modified primary constructor - removed the sort parameter
 *                       4) Adjusted sort speeds due to Merge Sort (vs O(N^2))
 * @version 02/16/2010 - Changes to detect methods that don't throw the
 *                       expected DictionaryException.
 *                     - Also fine-tuned the timing code for performance.
 */
 
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class P5TestDriver
{
   private static final String RESULTS_FOR = "Results for Program 5";
   
   public static void main(String[] args)
   throws ClassNotFoundException,
          FileNotFoundException
   {
      boolean pass = true;

      printHeader(args);

      // Test architectures...
      pass &= testDictionaryExceptionArch();
      pass &= testDictionaryArch();     

      System.out.println();

      // Test various dictionaries...
      pass &= testDefaultDictionary();
      pass &= testSpecifiedDictionary("randomDictionary.txt",
                                      178689,
                                      RANDOM_WORDS,
                                      3500000000L,
                                      16000000);
      // Test the write method...
      pass &= testWrite();
      
      printResults(pass);
   }

   private static boolean testDictionaryExceptionArch()
   throws ClassNotFoundException,
          FileNotFoundException
   {
      System.out.println("DictionaryException architecture tests...");

      boolean pass = true;
      int cnt;
      Class cl = DictionaryException.class;
      Class[] temp;
      
      pass &= test(cl.getSuperclass() == Class.forName("java.lang.Exception"),
                   "Class extends something other than Exception");
      pass &= test(cl.getConstructors().length == 2,
                   "Incorrect number of constructors");

      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);     
      pass &= test(cnt == 0, "Incorrect number of public methods");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 0, "public instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, "Protected instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 0, "Incorrect number of instance fields declared");
      
      // Count and test number of of PACKAGE fields
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);
      pass &= test(cnt == 0, "package fields or constants declared");

      return pass;
   }
  
   private static boolean testDictionaryArch()
   throws ClassNotFoundException,
          FileNotFoundException
   {
      System.out.println("Dictionary architecture tests...");

      boolean pass = true;
      int cnt;
      Class cl = Dictionary.class;
      Class[] temp;
      
      pass &= test(cl.getSuperclass() == Class.forName("java.lang.Object"),
                   "Class extends something other than Object");
      pass &= test(cl.getConstructors().length == 2,
                   "Incorrect number of constructors");

      Type[] types = cl.getGenericInterfaces();
      pass &= test(types.length == 1, "Expected 1 interface, found " + types.length);
      if (types.length == 1)
      {
         pass &= test(types[0].toString().equals("java.lang.Iterable<java.lang.String>"),
                      "Not implementing java.lang.Iterable<String> as expected");
      }

      pass &= test(useOf("Collections.", "Dictionary.java"),
                   "Importing or using the Collections class is not allowed");

      pass &= test(useOf("Arrays.", "Dictionary.java"),
                   "Importing or using the Collections class is not allowed");

      pass &= test(useOf("@SuppressWarnings", "Dictionary.java"),
                   "suppression of warnings is not allowed");

      pass &= test(useOf("[", "Dictionary.java"),
                   "Use of arrays is not allowed");

      String[] names = {"add", "iterator","lookUp","write"};

      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);     
      pass &= test(cnt == names.length, "Incorrect number of public methods");
      pass &= test(verifyNames(cl.getDeclaredMethods(), Modifier.PUBLIC, names),
                   "Unspecified method name(s)");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 0, "public instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, "Protected instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 1, "Incorrect number of instance fields declared");
      
      // Count and test number of of PACKAGE fields
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);
      pass &= test(cnt == 0, "package fields or constants declared");

      return pass;
   }
  
   private static boolean testDefaultDictionary()
   {
      boolean pass = true;

      System.out.println("Testing Dictionary using Dicitonary()...");

      // Test default constructor...
      Dictionary dict = new Dictionary();
      pass &= testDict(dict, 0, new String[0], 0);
      pass &= testAdd(dict, 0);

      return pass;
   }

   private static boolean testSpecifiedDictionary(String fname,
                                                  int expectedSize,
                                                  String[] expectedWords,
                                                  long sortLimit,
                                                  long searchLimit)
   {
      boolean pass = true;
      Dictionary dict = null;
      long start = 0;

      System.out.println("Testing Dictionary using Dictionary(\"" + fname + "\")...");

      if (sortLimit > 0)
      {
         System.out.println("\n   Sorting expected to take less than "
                          + sortLimit / 1000000000.0
                          + " seconds on unix1 (2, 3, or 4)");
         System.out.println("   for " + expectedSize + " words");
         System.out.println("   BE SURE to test there - that is where your "
                          + "code will be graded!\n");
      }

      try
      {
         if (sortLimit > 0)
         {
            start = System.nanoTime();
         }

         dict = new Dictionary(fname);

         if (sortLimit > 0)
         {
            long time = System.nanoTime() - start;
            //System.out.println("Sort took " + time / 1000000000.0 + " seconds");

            pass &= test(time < sortLimit,
                         "expected sort time less than " + sortLimit / 1000000000.0
                         + " seconds, found " + time / 1000000000.0);
         }
         
         pass &= testDict(dict, expectedSize, expectedWords, searchLimit);
         pass &= testAdd(dict, expectedSize);
      }
      catch (DictionaryException e)
      {
         pass &= test(false, "Throwing unexpected exception");
      }

      return pass;
   }

   private static boolean testAdd(Dictionary dict, int size)
   {
      boolean pass = true;
      int z;

      String[] words = new String[] {"ZZZZ", "AAAA", "MMMM", "EEEE", "QQQQ"};

      for (int i = 0; i < words.length; i++)
      {
         // Add it twice - should only add first one...
         dict.add(words[i]);
         dict.add(words[i]);

         // Check size...
         int expect = size + 1 + i;
         int actual = size(dict);
         pass &= test(actual == expect, "dictionary is wrong size after add(), expected "
                                       + expect + ", found " + actual);

         // Check in order after add...
         pass &= test(isOrdered(dict), "dictionary is out of order after add()");

         // Search for word...
         pass &= test(dict.lookUp(words[i]), "could not find word after call to add()");
      }

      return pass;
   }

   private static boolean testWrite()
   {
      final String DICT = "DictionaryWrittenByYOU.txt";
      boolean pass = true;

      // Make a default dictionary..
      Dictionary dict = new Dictionary();

      // Add words from the random array to local array...
      String[] words = new String[RANDOM_WORDS.length];

      for (int i = 0; i < RANDOM_WORDS.length; i++)
      {
         words[i] = RANDOM_WORDS[i];
      }

      // Sort and add words to dictionary (in order makes more efficient!)
      Arrays.sort(words);

      for (int i = 0; i < words.length; i++)
      {
         dict.add(words[i]);
      }

      // Write it...
      try
      {
         dict.write(DICT);
      }
      catch(DictionaryException e)
      {
         pass = test(false, "unexpected DictionaryException while testing write()");
      }

      // Test it...
      pass &= testSpecifiedDictionary(DICT, RANDOM_WORDS.length, RANDOM_WORDS, 0, 0);

      return pass;
   }
   
   private static boolean testDict(Dictionary dict,
                          int expectedSize,
                          String[] expectedWords, // expect non-null, but empty okay!
                          long searchLimit)       // 0 to not test search time
   {
      boolean pass = true;
      long start = 0;
      
      pass &= test(size(dict) == expectedSize, "Dictionary is not expected size");     
      pass &= test(isOrdered(dict), "Dictionary is out of order");
     
      if (searchLimit > 0)
      {
         System.out.println("   lookUp expected to take less than "
                          + searchLimit / 1000000000.0
                          + " seconds on unix1 (2, 3, or 4)");
         System.out.println("   for " + expectedWords.length + " words.");
         System.out.println("   BE SURE to test there - that is where your "
                          + "code will be graded!\n");

         start = System.nanoTime();
      }

      pass &= testLookUp(dict, expectedWords);

      if (searchLimit > 0)
      {
         long time = System.nanoTime() - start;
         //System.out.println("Lookup took " + time / 1000000000.0 + " seconds");

         pass &= test(time < searchLimit,
                      "expected lookUp time less than "
                      + searchLimit / 1000000000.0
                      + " seconds, found " + time / 1000000000.0);
      }

      return pass;
   }
   
   private static int size(Dictionary dict)
   {
      int size = 0;  
      
      // The is the Java for-each statement.
      // Requires that you have implemented the Iterable interface correctly!
      for (String s : dict)
      {
         size++;
      }
   
      return size;
   }
   
   private static boolean isOrdered(Dictionary dict)
   {
      Iterator<String> it = dict.iterator();
      
      if (!it.hasNext())
      {
         // List is empty!
         return true;
      }

      String a = it.next();
      
      while (it.hasNext())
      {
         String b = it.next();
         
         if (a.compareTo(b) >= 0) // Assumes no duplicates!
         {
            return false;
         }
         
         a = b;
      }
      
      return true;
   }

   private static boolean testLookUp(Dictionary dict, String[] words)
   {
      if (words == null)
      {
         words = getArray(dict);
      }

      boolean pass = true;
      
      for (String s : words) 
      {
         pass &= test(dict.lookUp(s), "lookUp(" + s + ")");
                  
         if (!pass)
         {
            return false;
         }
      }

      // Look for something not in there...
      pass &= test(!dict.lookUp("FARFIGNEWTON"),
                   "lookUp(FARFIGNEWTON) should not be in the dictionary");   
      
      return pass;
   }

   private static String[] getArray(Dictionary dict)
   {
      int size = 0;

      // Make a array of words in none were provided...
      ArrayList<String> list = new ArrayList<String>();

      for(String s : dict)
      {
         list.add(s);
         size++;
      }

      String[] array = new String[size];

      return list.toArray(array);
   }

   private static void printHeader(String[] args)
   {
      if (args.length == 1)
      {
         System.out.println(args[0]);
      }
      
      System.out.println(RESULTS_FOR + "\n");
   }
   
   private static void printResults(boolean pass)
   {
      String msg;
      
      if(pass)
      {
         msg = "\nCongratulations, you passed all the tests!\n\n"
            + "Your grade will be based on when you turn in your functionally\n"
            + "correct solution and any deductions for the quality of your\n"
            + "implementation. Quality is based on, but not limited to,\n"
            + "coding style, documentation requirements, compiler warnings,\n"
            + "and the efficiency and elegance of your code.\n";
      }
      else
      {
         msg = "\nNot done yet - you failed one or more tests!\n";
      }
      
      System.out.print(msg);       
   }
   
   private static int countModifiers(Field[] fields, int modifier)
   {
      int count = 0;
      
      for (Field f : fields)
      {
         if (f.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }
   
   private static int countModifiers(Method[] methods, int modifier)
   {
      int count = 0;
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }
   
   private static boolean approx(double a, double b, double epsilon)
   {
      return Math.abs(a - b) < epsilon;
   }
   
   private static boolean verifyNames(Method[] methods, int modifier, String[] names)
   {
      boolean pass = true;
      Arrays.sort(names);
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            if (Arrays.binarySearch(names, m.getName()) < 0)
            {
               System.out.print("      Class contains unspecified public ");
               System.out.println("method: " + m.getName());
               pass &= false;
            }
         }
      }
      
      return pass;
   }
      
   private static boolean test(boolean pass, String msg)
   {
      if (!pass)
      {
         Throwable t = new Throwable("Failed - " + msg);
         t.printStackTrace();
      }
      
      return pass;
   }
   
   private static boolean useOf(String match, String fname) throws FileNotFoundException
   {
      Scanner scanner = new Scanner(new File(fname));
         
      while (scanner.hasNextLine())
      {
         String line = scanner.nextLine();
         
         if (line.contains(match))
         {
            // Using, importing, or referring to something you should not be
            scanner.close();
            return false;
         }
      }
      
      scanner.close();
      
      // Pass - not found...
      return true;
   }
   
   private static final String[] RANDOM_WORDS = new String[]
   {
      "SONANCES",
      "OUTSLEEP",
      "BREECHLOADERS",
      "SUPPLED",
      "INVIABLY",
      "TWINIEST",
      "REDLINERS",
      "CUPULE",
      "BOPEEPS",
      "FELSITIC",
      "PLANTABLE",
      "TUMEFIED",
      "GERMANDER",
      "DATELESS",
      "STAKEHOLDERS",
      "COMMIX",
      "DEMOGRAPHIC",
      "TSATSKE",
      "SHOPBOYS",
      "TRIMS",
      "NONLANGUAGES",
      "SHAWM",
      "REBORE",
      "PRESBYOPIA",
      "SATISFACTORILY",
      "KRILL",
      "DYARCHIC",
      "TRICKLED",
      "BASKETS",
      "TAXONOMIES",
      "CENTIGRADE",
      "CLUBABLE",
      "OUTVOTING",
      "POOHING",
      "MULTILEVEL",
      "NUTCASE",
      "EXEMPLUM",
      "FOOZLE",
      "TRANSEUNT",
      "OVERTAX",
      "MINDS",
      "INCOMPLETELY",
      "FEATHERBEDDINGS",
      "BLOWHOLES",
      "JETSOM",
      "BACKSHORE",
      "MARABOUTS",
      "CONTUSED",
      "TRAMELING",
      "OVERPRINTS",
      "STRETCHABLE",
      "STATUED",
      "AULD",
      "REELIGIBILITIES",
      "MORGUES",
      "GLASSIES",
      "PERISHED",
      "FLANNELLY",
      "BIRADIAL",
      "UNWILLINGLY",
      "AIRWAY",
      "NONYL",
      "LINURONS",
      "LABIALIZES",
      "FLUORESCER",
      "TZARIST",
      "SWEETSOPS",
      "BRIGADIERS",
      "FLYSCH",
      "AUCTIONEER",
      "UNTIMELIER",
      "SAXATILE",
      "IMPUGNING",
      "COADMITTED",
      "MORALLY",
      "MULTITUDINOUS",
      "SPORTY",
      "MISMANAGED",
      "BATHYAL",
      "DOGSLEDS",
      "WALLEYED",
      "HEMORRHOIDALS",
      "PRECIPE",
      "HISPANIDADS",
      "ABRADE",
      "AFFLUENCE",
      "THREADFIN",
      "STARES",
      "BURSTING",
      "CADUCEAN",
      "TRACHEARY",
      "SLAPHAPPIER",
      "PROGRAMS",
      "UTOPIANISMS",
      "REMOVABLE",
      "RESORCINOLS",
      "TRIABLE",
      "EEL",
      "TOKE",
      "HONCHO",
      "SUNDIALS",
      "RAVELED",
      "FRESHING",
      "OTIOSENESSES",
      "ADVISEE",
      "PREZ",
      "INTERPLANTING",
      "CROSSCUTS",
      "OVERPERSUASIONS",
      "SISTERING",
      "GLOATS",
      "DUMBHEADS",
      "HIERURGY",
      "COBBERS",
      "TOMAHAWKING",
      "UNBONNET",
      "UNSTERILE",
      "GALLEON",
      "BERHYMES",
      "CONNS",
      "BULLYRAG",
      "ORGANZA",
      "SLAUGHTEROUSLY",
      "PAUSES",
      "LUSHLY",
      "STUDBOOKS",
      "CONDUCTRESSES",
      "OUTSAYING",
      "POCKILY",
      "TUTORAGES",
      "FLAMES",
      "COCOONINGS",
      "MENSURATION",
      "SUCKED",
      "HEARKENING",
      "REFUGIUM",
      "UNPLAITED",
      "SYMPTOMATOLOGY",
      "SCALADE",
      "FUNFEST",
      "SANIDINES",
      "FRAMABLE",
      "PODIA",
      "TAILER",
      "DENATIONALIZE",
      "JIMMIE",
      "MAIDENHEAD",
      "AUTHENTICITY",
      "EIGHTIETHS",
      "DELS",
      "LADYSHIPS",
      "IVORY",
      "TARANTELLA",
      "TREMULANT",
      "BEATIFIC",
      "SHALEY",
      "CALAMATAS",
      "VIOLONCELLOS",
      "NUMERATED",
      "UNINFORMATIVE",
      "UNSAVED",
      "NITROS",
      "SALIMETER",
      "CONCERTEDLY",
      "ELECTRODYNAMICS",
      "BADDER",
      "INSTIGATES",
      "BUTTERNUT",
      "LAMINATORS",
      "OVIPOSITING",
      "BLACKBIRDS",
      "EUPHRASIES",
      "DESULFURIZING",
      "ROUGHING",
      "PROVERBING",
      "EMBRACED",
      "PECKING",
      "RURALITE",
      "OLESTRAS",
      "POLIOVIRUSES",
      "PROSTATISMS",
      "NONFACTOR",
      "ATRETIC",
      "DEGUSTATION",
      "INGATHERINGS",
      "CORRESPONDENCES",
      "CHORAGI",
      "REEMBROIDERING",
      "ABSURDISTS",
      "FERRULED",
      "CAPRICE",
      "WHODUNIT",
      "SEXTUPLY",
      "LEGISLATIVE",
      "NONACQUISITIVE",
      "OYSTERCATCHERS",
      "METASTABILITIES",
      "TEDIUMS",
      "PROTESTORS",
      "MICROFORM",
      "CROWSTEPS",
      "BLUFFEST",
      "PUNKIEST",
      "LIARD",
      "SEGS",
      "SPIRALLING",
      "ESTABLISHER",
      "BRAWLER",
      "MAMMOGRAPHIC",
      "FILEFISH",
      "LINELESS",
      "HANDS",
      "PENCEL",
      "HOSES",
      "RESEAT",
      "LIMB",
      "WRY",
      "STEERS",
      "CASEWORKERS",
      "RESTABLE",
      "CONSUETUDINARY",
      "DEOXYRIBOSES",
      "OESTRIOL",
      "NONCOMPOSER",
      "SPESSARTINE",
      "SUPERGRAVITY",
      "TELAMON",
      "EXPRESSIONLESS",
      "IMMUNOGLOBULIN",
      "SCATHED",
      "BEDFAST",
      "OVERHEATS",
      "MISBILLING",
      "LANDER",
      "UNALLEGED",
      "STEATOPYGOUS",
      "ARIARY",
      "LASCIVIOUSNESS",
      "AMPLIFIERS",
      "FLOTATION",
      "TINHORNS",
      "ADVECTIONS",
      "GUIDED",
      "CALYPSO",
      "FALDERALS",
      "INDOMITABLE",
      "SKIBOBBING",
      "MICROTONALLY",
      "OVERACTED",
      "FERVIDITIES",
      "ABLER",
      "PORTS",
      "CARPETBAGS",
      "LESSENING",
      "GLOBETROTTING",
      "VASTY",
      "GLUEPOT",
      "DILIGENCES",
      "INDEFINITES",
      "BUMP",
      "BIOLOGICALLY",
      "YELLOWWARE",
      "APPEASABLE",
      "BABYSAT",
      "FABRICATED",
      "BINGED",
      "LENITED",
      "PREDISPOSING",
      "SPACER",
      "GENEROSITY",
      "WHISTLES",
      "COPUBLISHERS",
      "EQUIDS",
      "BACKDATED",
      "EOLOPILES",
      "WINTRY",
      "UNEVENTFUL",
      "RESIDENTIAL",
      "ANTIMATTER",
      "CLARINETISTS",
      "CULMINATION",
      "BOMBINATING",
      "ANTIRADICAL",
      "GILLERS",
      "DINOSAURS",
      "STYLITE",
      "BOUSING",
      "SCHOOLKIDS",
      "HACKMATACKS",
      "CRUSTLESS",
      "FARFALS",
      "HEDONISTS",
      "ELFINS",
      "HALAZONES",
      "LIDO",
      "CORRUPTED",
      "ARBITRARINESS",
      "RUBELLA",
      "OKRAS",
      "SINISTRAL",
      "FULLBACK",
      "LAUDS",
      "EVISCERATED",
      "ASCENDANCES",
      "OSMIC",
      "BALDER",
      "CIVILIZE",
      "VEALIEST",
      "SYNONYMISTS",
      "LONGSHORE",
      "CICATRICIAL",
      "GODFATHERS",
      "MULLEIN",
      "WIDGETS",
      "BOBTAILED",
      "GULLY",
      "ACRIDITY",
      "KINGED",
      "PIGHEADEDNESS",
      "EPILIMNIA",
      "UPBIND",
      "FLORIDNESSES",
      "GENTAMICINS",
      "SPEARERS",
      "KOSHERS",
      "ESTRAYING",
      "BEELINES",
      "DERISORY",
      "SILVER",
      "HELICOPTED",
      "EMYDS",
      "SIMPLIFIER",
      "YODLER",
      "DHARMA",
      "OROGRAPHIC",
      "CABINMATES",
      "TIDBITS",
      "INTUBATION",
      "AUTUMNALLY",
      "PRISMS",
      "CYSTEIN",
      "SUPERMINDS",
      "FOREARMED",
      "PROFESSORSHIP",
      "COMPARATIVELY",
      "PYGMOID",
      "AVUNCULARITY",
      "FRAYING",
      "SIGHTLIEST",
      "RISOTTOS",
      "TRIUNITIES",
      "MINIDRESS",
      "CHESS",
      "LIGURE",
      "SNEESH",
      "COMFORTS",
      "SOLD",
      "FORTISSIMOS",
      "MATTEDLY",
      "EMENDED",
      "GYPSIES",
      "DAUPHINE",
      "SLOVENLIER",
      "OVERDOMINANT",
      "HYPERGAMY",
      "PARRIDGE",
      "EXCLUDABILITIES",
      "TRIWEEKLY",
      "COWHIDED",
      "SQUABS",
      "STRAVAGING",
      "VARICOSED",
      "HYPERPLOID",
      "INTENSITIES",
      "ERYTHROPOIESIS",
      "SERVOMOTORS",
      "LISSOME",
      "ROCKHOUND",
      "PARFLECHES",
      "OVERSTEPS",
      "HERMITISM",
      "PISHOGUES",
      "CIRCUMFUSIONS",
      "JAYHAWKERS",
      "PERFORMABLE",
      "DECISIVENESSES",
      "TELEVANGELISM",
      "PLASMAGENES",
      "BORECOLE",
      "ALLOMORPHIC",
      "VENTIFACTS",
      "COFFLING",
      "PERSEVERATIONS",
      "TRANSLATIONAL",
      "NONRESPONDENT",
      "ERECTNESS",
      "STOCKBROKERAGE",
      "COLLEAGUES",
      "REGIMENTED",
      "SCUMMIEST",
      "UPGRADABLE",
      "CRYPTOGRAPHS",
      "CARBURISE",
      "DIPT",
      "DISBURSER",
      "PREVISITS",
      "WEAVER",
      "UNDERSIZE",
      "DEADPANS",
      "GOMERAL",
      "CONCURRENCES",
      "FINGERPRINT",
      "INTERRUPTS",
      "SCHEMATIZATIONS",
      "ROOFTREES",
      "ELYTRUM",
      "ARCHETYPE",
      "CRIED",
      "BLOGS",
      "MANGANESE",
      "ORDINAND",
      "POLYETHYLENES",
      "HAT",
      "PROCLAIMERS",
      "STROPS",
      "CALCULATOR",
      "HELLHOLE",
      "ANIMATO",
      "DOBIES",
      "FECULENCE",
      "DOGIES",
      "VILLAINOUS",
      "UNGUIDED",
      "STINGY",
      "BRAZERS",
      "CAMERAE",
      "LEATHERNECK",
      "RECRATED",
      "KIDNAPEES",
      "FOOTBAG",
      "TEPEES",
      "SALABLY",
      "FROSTLINE",
      "OPHIUROIDS",
      "UTTERABLE",
      "ARILLODE",
      "JOHNBOATS",
      "LOVABLENESS",
      "RUTHERFORDIUMS",
      "MOUILLE",
      "WINTERING",
      "MUCKRAKING",
      "FIELDSMEN",
      "AVIONS",
      "CHASTER",
      "REPUGNING",
      "BANGKOKS",
      "TOURERS",
      "RIBONUCLEOSIDE",
      "HEWING",
      "MAINTOP",
      "OVERGRAZES",
      "DEPROGRAM",
      "MALATHIONS",
      "TURBOTS",
      "ULTRACAUTIOUS",
      "IRREFUTABLE",
      "NAGANA",
      "AGOROTH",
      "GONGS",
      "LAWFULNESS",
      "DECORTICATES",
      "MACROFOSSIL",
      "CYBERNAUTS",
      "OUTPREENING",
      "SNOOTILY",
      "INTERLINING",
      "GEEPOUNDS",
      "CARESSER",
      "ANGELUSES",
      "OVERBUSY",
      "QUARANTINED",
      "ASPECTS",
      "MARBLEIZING",
      "INCESTUOUSLY",
      "PRESPECIFIED",
      "EXODERMIS",
      "MUSTARDY",
      "MAXILLAS",
      "ATHEISTICALLY",
      "ROTTER",
      "NOWNESSES",
      "HUSHFUL",
      "DUOMI",
      "CRABBEDLY",
      "WILDWOODS",
      "FORESIGHTS",
      "SHROUDING",
      "PRONUNCIATION",
      "SESTINAS",
      "DEDUCTED",
      "AEROFOILS",
      "REMARRY",
      "INSPECTORATES",
      "OUTDREAM",
      "LIVESTOCK",
      "PRECIOUSLY",
      "BRONZER",
      "KEYWAYS",
      "SARDIUS",
      "SUMMONED",
      "SPARGER",
      "PACTION",
      "PEEWEES",
      "CHILLIEST",
      "MADERIZES",
      "SALUTED",
      "WIZARDRIES",
      "PROPHASE",
      "PATRONIZING",
      "DEMISES",
      "FIREMAN",
      "SEMANTICAL",
      "SIBB",
      "REIVERS",
      "PITYING",
      "SYMBOLOGY",
      "DAMMING",
      "ACUTANCES",
      "SUBREPTIONS",
      "KNOTTER",
      "TENDING",
      "BROLLIES",
      "PRESLICES",
      "VOLCANOS",
      "JUMBAL",
      "SULFUROUSLY",
      "LANIARD",
      "KAFFEEKLATSCH",
      "DESICCATORS",
      "PREFIGURED",
      "WARDEN",
      "JUDGED",
      "SUBSTITUTIVE",
      "LYRISM",
      "SMALLISH",
      "REREPEATED",
      "PLEBE",
      "LEAPS",
      "PINTAIL",
      "BOOKKEEPERS",
      "SKIRTLIKE",
      "PRENOMENS",
      "PURPORTED",
      "CURLYCUE",
      "UPTEARING",
      "OUTHANDLED",
      "EPOXIDES",
      "ADENOIDAL",
      "TRAVELOGUE",
      "FIANCEES",
      "OUTBREEDS",
      "DIFFUSIVE",
      "WRINKLE",
      "OUTGLOW",
      "DISHWATERS",
      "HOARILY",
      "TUFACEOUS",
      "SHOTTING",
      "PEDAGOGS",
      "WHEREWITHS",
      "REDEFINITION",
      "RETELL",
      "LUNCHEONETTES",
      "REPOPULARIZES",
      "TERRAFORMED",
      "GOWDS",
      "ASSORTED",
      "KAONIC",
      "GAMESTERS",
      "JALOUSIES",
      "ARRANGE",
      "KEBBIES",
      "DEFICIT",
      "DESIRABILITY",
      "EBONIZING",
      "TINFOIL",
      "LOCATOR",
      "SMOKEHOUSE",
      "TRUING",
      "PENTAMEROUS",
      "KITTLES",
      "CASUIST",
      "ALTERNANTS",
      "REFINERS",
      "PUSHCART",
      "VITAMER",
      "SPIFFILY",
      "DIVERTED",
      "VICOMTES",
      "TOOLROOMS",
      "BURLIER",
      "QADIS",
      "RAJAS",
      "BETAS",
      "PAINCH",
      "ADJUDICATES",
      "REFORMATIONS",
      "NIXING",
      "MISALLOCATES",
      "DEVOTEDLY",
      "BOILOVER",
      "COUPONS",
      "SLIPFORMS",
      "IMPROVISATORY",
      "UNLATCH",
      "WHAPS",
      "KENNELING",
      "UNBRAIDED",
      "DISEUR",
      "HEREDITARY",
      "SNAKEY",
      "FRONTALLY",
      "FISTIC",
      "EUDIOMETERS",
      "SUBLIMES",
      "PREMIER",
      "MOTIVELESSLY",
      "MELANCHOLICS",
      "BOYS",
      "FLEURY",
      "EMPHASISE",
      "TOUCHLINE",
      "CREWED",
      "SWOOSHING",
      "PRAOS",
      "XYLOPHAGE",
      "COELOMIC",
      "WELDMENTS",
      "ISTHMIC",
      "PLEURISY",
      "MYCOPHAGISTS",
      "MILITATES",
      "QUADS",
      "CUTGRASS",
      "APPROXIMATED",
      "THUNDERSTRIKE",
      "OPPOSITIONISTS",
      "CITRONELLALS",
      "DISCLAIMED",
      "QUELEA",
      "GIBBONS",
      "FLINTHEADS",
      "UNMEMORABLY",
      "DETONATIVE",
      "PIEZOMETERS",
      "ROBORANTS",
      "STETSON",
      "ONOMASTIC",
      "CYCLIZINES",
      "LOCUM",
      "NIELLOED",
      "AMIRS",
      "LOWLIEST",
      "PHOTOREACTIONS",
      "EVADES",
      "FORGIVABLY",
      "GAPEWORMS",
      "NONOXIDIZING",
      "EGOCENTRIC",
      "CANTONMENTS",
      "NONDIMENSIONAL",
      "IMPOSTER",
      "SERF",
      "ABASHED",
      "NASCENCES",
      "SHEEPISHNESSES",
      "BOUNTEOUSNESS",
      "CHORUSED",
      "LORICATE",
      "PULSATOR",
      "ACETIFICATIONS",
      "BRUSK",
      "MUSEUMS",
      "PASSIVATION",
      "SWANPANS",
      "RECITAL",
      "UNCHANGING",
      "CHAPMAN",
      "AGGRADATIONS",
      "AIMS",
      "GITS",
      "IROKOS",
      "BANKROLLER",
      "CANONRIES",
      "STOREKEEPER",
      "MEROPIA",
      "HALOPHYTIC",
      "ECONOMETRICIAN",
      "TATTING",
      "CANNABIS",
      "TANTALOUS",
      "POWDERING",
      "ISOGONAL",
      "WEAL",
      "ANTIDESICCANT",
      "MONOCULARS",
      "CORESEARCHERS",
      "UNCOLLECTIBLES",
      "BLEMISHER",
      "INTIMATERS",
      "VIBRISSAE",
      "PERCEPTUALLY",
      "ASPISH",
      "HYMNODIST",
      "GAINERS",
      "UREAS",
      "COUNTERSNIPER",
      "EXOSPORIUM",
      "ENRAGEDLY",
      "GATECRASH",
      "FUZED",
      "HEADHUNTS",
      "BUGLERS",
      "RADIOLOGIC",
      "WOODLAND",
      "COLONES",
      "INTERCEPTERS",
      "SPUNKILY",
      "MILDEWING",
      "SOARINGS",
      "SOUPLIKE",
      "DAINTINESS",
      "BLACKNESS",
      "CLERISIES",
      "PASTURING",
      "CHANTEYS",
      "SCORNED",
      "PSEUDOCOELOMATE",
      "ENTHETIC",
      "RENUMBERED",
      "ANYBODIES",
      "PTOTIC",
      "HARRIER",
      "DIFFRACTOMETERS",
      "SURBASE",
      "FURNISHER",
      "UNRECOVERED",
      "DECALOGUE",
      "TITLES",
      "POPPADUM",
      "FADEAWAY",
      "WOOLWORKS",
      "FOUNTAINHEAD",
      "DEFORESTS",
      "PSYCHOSOMATICS",
      "POTENTIATES",
      "BARRATORS",
      "SADDLECLOTH",
      "MARSEILLE",
      "BARMEN",
      "NONSENSES",
      "CRATER",
      "RECONQUERING",
      "EXTRADITION",
      "SUBVIRUSES",
      "PHOTO",
      "SHOAL",
      "HIPPOPOTAMUS",
      "RUGA",
      "SANSERIFS",
      "SENTENCE",
      "ULTRAVACUUMS",
      "TABARD",
      "COMANAGEMENTS",
      "FATIGUE",
      "INVALUABLE",
      "SHICKSA",
      "MASALA",
      "UNDISSOCIATED",
      "NEWSBEATS",
      "QUINTANS",
      "ALUM",
      "MASTIFFS",
      "PLUG",
      "ANTHOPHYLLITES",
      "MELODICALLY",
      "QUOLL",
      "GASSED",
      "ANDROGENIC",
      "INSERTERS",
      "ACCEPTEDLY",
      "WATERSHEDS",
      "EXPLOITS",
      "INLAND",
      "CARRY",
      "INITIALIZATIONS",
      "PALAZZO",
      "HARBOURS",
      "POLYPLOID",
      "FAMILIARITIES",
      "REPLENISHERS",
      "PALPATORY",
      "UNGRACIOUS",
      "BIRDER",
      "ACEDIA",
      "POSTULATORS",
      "REMOTIVATED",
      "HOLLAND",
      "RIGHTS",
      "POSTDIVESTITURE",
      "IMBOLDEN",
      "CONGLOBATED",
      "GRAMMAR",
      "RAMULOUS",
      "CREDITABLE",
      "MECHANIST",
      "DECARBURIZES",
      "RAGEE",
      "THRONES",
      "SUBENTRIES",
      "MEGALOMANIAC",
      "DIALOGERS",
      "MESSENGERING",
      "SQUIGGLED",
      "GROUNDLESSNESS",
      "BEACHBOYS",
      "SURVIVES",
      "DICKING",
      "SOJOURN",
      "DISENCHANTMENT",
      "HYDRANTH",
      "CLING",
      "CONTEST",
      "UNSALARIED",
      "SEMIRURAL",
      "COUNTERACT",
      "EXTRALITY",
      "ALCAYDE",
      "MISTLETOE",
      "DISOBEDIENCES",
      "COMPUTERLESS",
      "WORMROOTS",
      "GRANULOMATOUS",
      "METERSTICKS",
      "SOMATOSTATIN",
      "CLOQUES",
      "COSTMARY",
      "ALGAECIDES",
      "MOBILIZES",
      "SUPERSATURATE",
      "GRATIFICATION",
      "ENVIOUSNESSES",
      "CATHODALLY",
      "WATCHDOGGED",
      "RELEARNT",
      "DRAFTEE",
      "RATED",
      "RECOILING",
      "ENHALOING",
      "PAPARAZZI",
      "INFIELDS",
      "INFATUATING",
      "SCOUTHS",
      "COINSURED",
      "MEZEREUM",
      "WAKEBOARDS",
      "SCREENING",
      "BILEVELS",
      "OBLIGATO",
      "GUNSMITH",
      "RIDDLER",
      "WAGERS",
      "TOROSITY",
      "COMPLEMENTATION",
      "MISDOINGS",
      "THICKENED",
      "CAULIFLOWER",
      "VULCANISING",
      "DISTILLATION",
      "SEARCH",
      "ENDURER",
      "REWORDS",
      "PANDORE",
      "RAWNESSES",
      "MONOGAMOUS",
      "REVOKABLE",
      "PINKISHNESSES",
      "HYPOTHENUSES",
      "APOLOG",
      "PARCELED",
      "THERMIC",
      "BIANNUAL",
      "PICKUP",
      "BEGGARDOM",
      "IMPERMISSIBLE",
      "COPIHUES",
      "INELOQUENT",
      "GREENLINGS",
      "CHUBBINESSES",
      "SEMILETHAL",
      "INCONY",
      "MISHANDLE",
      "MISTAKEN",
      "SORAS",
      "HOW",
      "REINCORPORATION",
      "STOMODAEAL",
      "SUBCEILINGS",
      "RESTRICTIONISM",
      "TALLOW",
      "ODDNESS",
      "SPLODGE",
      "GUACHARO",
      "BICYCLIC",
      "CELEBRATIONS",
      "TAILSTOCK",
      "SPOILT",
      "PHRENITIDES",
      "HOOPING",
      "GREYNESSES",
      "PERSONNEL",
      "PETROUS",
      "SEDGES",
      "TESTUDO",
      "MISRENDERED",
      "PHONEY",
      "IMMOBILISMS",
      "THEREFORE",
      "OCCUPATION",
      "APICULUS",
      "SECLUDED",
      "HAMMED",
      "ENDOW",
      "REVERENCERS",
      "CONGRUITIES",
      "MOLL",
      "REVAMPER",
      "HUMANISTICALLY",
      "THAIRMS",
      "DONUT",
      "PHENACITES",
      "ALLEGORIZATION",
      "CENTRALISES",
      "BELIVE",
      "BENEFICES",
      "RETRENCHES",
      "DISSAVED",
      "GRITTED",
      "ILLUMED",
      "ENDEMIAL",
      "COOLANT",
      "UNDERCLOTHINGS",
      "DISSEIZEE",
      "REHOSPITALIZES",
      "VIZARDS",
      "NEUTRINOS",
      "CONTINUITY",
      "HOMOGENEITY",
      "IDENTIFYING",
      "AMETHYST",
      "PHALANGERS",
      "MONETARILY",
      "RECEMENTS",
      "CHLAMYDIAL",
      "HOROSCOPES",
      "NAVIGATES",
      "BELT",
      "UNDERHEAT",
      "CERVELAT",
      "DORONICUMS",
      "VALKYRIES",
      "CHIROMANCY",
      "ICHTHYOFAUNA",
      "DOVES",
      "VERNIERS",
      "HOUSECLEANED",
      "REAPABLE",
      "ANSWERABLE",
      "DOWNINESSES",
      "READABILITIES",
      "MOONBEAM",
      "MYRMECOPHILES",
      "VERBILE",
      "PARACRINE",
      "CHEERER",
      "ANAGRAMMATIZES",
      "OBOVATE",
      "CHAIS",
      "DRAWERS",
      "NORTRIPTYLINE",
      "NEPOTISTS",
      "NEPHRISMS",
      "NELLIES",
      "NEBULE",
      "EARNERS",
      "ANALYSED",
      "DUFUSES",
      "DRYABLE",
      "DROPSHOTS"
   };    
}
