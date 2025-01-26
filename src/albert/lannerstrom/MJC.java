package albert.lannerstrom;

import com.formdev.flatlaf.FlatDarculaLaf;
import net.miginfocom.swing.MigLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class MJC {
    JFrame frame;
    JPanel background;
    JPanel foreground;
    JButton nextOpenButton;
    JButton currentOpenButton;
    JButton finishedOpenButton;
    JButton createFileButton;

    private static String loadPath = ".";
    private static String savePath = ".";

    public static String OUTPUT_FILE_NAME;

    public static final CharSequence FAST_KEY = "FAST";
    public static final CharSequence MSD_KEY = "MSD";
    public static final int TRAY_LENGTH_KEY = 11;

    public static final String CART_TEMPLATE_PATH = "Support Files/html_templates/cart_template.html";
    public static final String COVER_PAGE_PATH = "Support Files/html_templates/cover_page.html";
    public static final String EXTRA_TEMPLATE_PATH = "Support Files/html_templates/extra_template.html";
    public static final String MATCHEDLIST_TEMPLATE_PATH = "Support Files/html_templates/matchedList_template.html";
    public static final String MSD_TEMPLATE_PATH = "Support Files/html_templates/msd_template.html";
    public static final String TEARDOWNMSD_TEMPLATE_PATH = "Support Files/html_templates/teardownMsd_template.html";
    public static final String TRAY_TEMPLATE_PATH = "Support Files/html_templates/tray_template.html";

    public static int MSD_AMOUNT;
    public static int FASTMATARE_AMOUNT;
    public static int DUPLICATE_AMOUNT;
    public static int TRAY_AMOUNT;
    public static int EXTRA_AMOUNT;
    public static int MATCHED_AMOUNT;
    public static int COMPONENT_AMOUNT;

    public static final String CABINET_KEY = "To cabinet";
    public static final String STORAGE_KEY = "To storage";
    public static final String SAVED_CABINET_KEY = "Saved in cabinet";

    public MultiJob setupMultiJob;
    public MultiJob inlineMultiJob;
    public MultiJob teardownMultiJob;

    public File setupMultiJobFile;
    public File inlineMultiJobFile;
    public File teardownMultiJobFile;

    public SetupDoc setupDoc;
    public static Boolean SINGLE_MULTIJOB_SETUP = false;

    public static void main(String[] args) {
        new MJC().startUp();
    }

    private void startUp() {
        buildGUI();
        // createSetupDoc();

    }

    private void buildGUI() {
        FlatDarculaLaf.install();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        // --- Initialize and create components ---
        frame = new JFrame();
        background = new JPanel();
        foreground = new JPanel();
        JPanel finishedPanel = new JPanel();
        JPanel currentPanel = new JPanel();
        JPanel nextPanel = new JPanel();

        nextOpenButton = new JButton("Choose file", UIManager.getIcon("Tree.openIcon"));
        JButton nextDeleteButton = new JButton(UIManager.getIcon("InternalFrame.closeIcon"));
        currentOpenButton = new JButton("Choose file", UIManager.getIcon("Tree.openIcon"));
        JButton currentDeleteButton = new JButton(UIManager.getIcon("InternalFrame.closeIcon"));
        finishedOpenButton = new JButton("Choose file", UIManager.getIcon("Tree.openIcon"));
        JButton finishedDeleteButton = new JButton(UIManager.getIcon("InternalFrame.closeIcon"));

        createFileButton = new JButton("Create setup report file", UIManager.getIcon("Tree.leafIcon"));

        Dimension buttonDimension = new Dimension(150, 36);

        // --- Next MultiJob Panel ---
        nextPanel.setLayout(new MigLayout());
        TitledBorder nextBorder = BorderFactory.createTitledBorder("Setup Multi Job Family");
        nextBorder.setTitleJustification(TitledBorder.CENTER);
        nextPanel.setBorder(nextBorder);
        nextOpenButton.setMinimumSize(buttonDimension);
        nextOpenButton.setName("next");
        nextDeleteButton.setName("next");
        nextPanel.add(nextOpenButton);
        nextPanel.add(nextDeleteButton);

        // --- Current MultiJob Panel ---
        currentPanel.setLayout(new MigLayout());
        TitledBorder currentBorder = BorderFactory.createTitledBorder("Inline Multi Job Family");
        currentBorder.setTitleJustification(TitledBorder.CENTER);
        currentPanel.setBorder(currentBorder);
        currentOpenButton.setMinimumSize(buttonDimension);
        currentOpenButton.setName("current");
        currentDeleteButton.setName("current");
        currentPanel.add(currentOpenButton);
        currentPanel.add(currentDeleteButton);

        // --- Finished MultiJob Panel ---
        finishedPanel.setLayout(new MigLayout());
        TitledBorder finishedBorder = BorderFactory.createTitledBorder("Teardown Multi Job Family");
        finishedBorder.setTitleJustification(TitledBorder.CENTER);
        finishedPanel.setBorder(finishedBorder);
        finishedOpenButton.setMinimumSize(buttonDimension);
        finishedOpenButton.setName("finished");
        finishedDeleteButton.setName("finished");
        finishedPanel.add(finishedOpenButton);
        finishedPanel.add(finishedDeleteButton);


        // --- Layout settings ---
        foreground.setLayout(new MigLayout(
                "",
                "[]25[]25[]",
                "[]"
        ));
        background.setLayout(new MigLayout(
                "",
                "[center]",
                "[]25[]"
        ));
        background.setBorder(new EmptyBorder(25, 25, 25, 25));

        // --- Set logo ---
        try {
            URL url = getClass().getResource("/logo/logo32x32.png");
            ImageIcon icon = new ImageIcon(url);
            frame.setIconImage(icon.getImage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not find logo.png file!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        // --- Menu ---
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Help");
        JMenuItem menuItem = new JMenuItem("About");

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "MultiJobComparator for Nexim\n" +
                                "© Albert Lännerström 2021\n\n" +
                                "Contact:\n" +
                                "Albert Lännerström\n" +
                                "076-8416523\n" +
                                "albert.lannerstrom@gmail.com\n\n" +
                                "Version: 1.0.0",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu.add(menuItem);
        menuBar.add(menu);


        // --- Finalizing code ---
        createFileButton.setMinimumSize(new Dimension(36, 36));

        frame.add(background);
        frame.setJMenuBar(menuBar);
        background.add(foreground, "wrap");
        foreground.add(nextPanel);
        foreground.add(currentPanel);
        foreground.add(finishedPanel);
        background.add(createFileButton);
        frame.setTitle("MultiJobComparator for Nexim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // --- Create File ActionListener ---
        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Make sure all files are different
                if (teardownMultiJobFile != null && inlineMultiJobFile != null && setupMultiJobFile != null) {
                    if ((teardownMultiJobFile.getName().equals(inlineMultiJobFile.getName())) || (inlineMultiJobFile.getName().equals(setupMultiJobFile.getName())) || (setupMultiJobFile.getName().equals(teardownMultiJobFile.getName()))) {
                        JOptionPane.showMessageDialog(frame, "You have to choose three DIFFERENT Multi Job files!", "File error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame, "Are you sure you have selected the correct files in the right order? \n" +
                                        setupMultiJobFile.getName() + "\n" +
                                        inlineMultiJobFile.getName() + "\n" +
                                        teardownMultiJobFile.getName(),
                                "Continue?", JOptionPane.YES_NO_OPTION)) {

                            createSetupDoc();
                        }
                    }
                } else {
                    if (setupMultiJobFile != null) {
                        Object[] options = {"OK", "Override"};
                        if (JOptionPane.NO_OPTION == JOptionPane.showOptionDialog(frame, "You have not chosen all three files yet!",
                                "File error",
                                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,
                                null,
                                options, options[0])) {

                            SINGLE_MULTIJOB_SETUP = true;
                            System.out.println("Test");
                            createSetupDoc();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "You have not chosen all three files yet!", "File error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // --- Open File ActionListeners ---
        finishedOpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile(e);
            }
        });

        currentOpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile(e);
            }
        });

        nextOpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile(e);
            }
        });

        // --- Delete File ActionListener ---
        finishedDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoadedFile(e);
            }
        });
        currentDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoadedFile(e);
            }
        });
        nextDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoadedFile(e);
            }
        });

    }

    private void loadFile(ActionEvent event) {
        //--- Load previously saved state (if i exists) ---
        try {
            File loadFile = new File("Support Files/LoadPath.txt");
            BufferedReader reader = new BufferedReader(new FileReader(loadFile));
            String line = null;
            if ((line = reader.readLine()) != null) {
                loadPath = line;
            }
            reader.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not load previously saved file path! \"Support Files/LoadPath.txt\" is missing and will be created on the next try", "Could not load file path", JOptionPane.WARNING_MESSAGE);
        }

        //--- Main code ---
        try {
            //Get path
            File path = new File(loadPath);
            //Create file chooser
            JFileChooser fileOpen = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".html, .htm", "htm", "html");
            fileOpen.setCurrentDirectory(path);
            fileOpen.setFileFilter(filter);

            // --- Sorting ---
            Action details = fileOpen.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);

            //Make sure a file is chosen
            if (fileOpen.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fileOpen.getSelectedFile();
                if (file != null) {
                    //Find out which button was clicked and assign the chosen file
                    JButton sourceButton = (JButton) event.getSource();
                    String fileName = file.getName();
                    switch (sourceButton.getName()) {
                        case "finished":
                            teardownMultiJobFile = file;
                            sourceButton.setText(fileName);
                            break;
                        case "current":
                            inlineMultiJobFile = file;
                            sourceButton.setText(fileName);
                            break;
                        case "next":
                            setupMultiJobFile = file;
                            sourceButton.setText(fileName);
                            break;
                    }
                }
                // --- Save loaded path to loadPath class field ---
                try {
                    FileWriter writer = new FileWriter("Support Files/LoadPath.txt");
                    writer.write(file.getParent());
                    loadPath = file.getParent();
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Could not save file path!", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not open file chooser dialog or could not locate path folder", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void deleteLoadedFile(ActionEvent event) {
        JButton sourceButton = (JButton) event.getSource();
        switch (sourceButton.getName()) {
            case "finished":
                teardownMultiJobFile = null;
                finishedOpenButton.setText("Choose file");
                break;
            case "current":
                inlineMultiJobFile = null;
                currentOpenButton.setText("Choose file");
                break;
            case "next":
                setupMultiJobFile = null;
                nextOpenButton.setText("Choose file");
                break;
        }
    }

    private void createSetupDoc() {
        //Create multijob objects from loaded files
        setupMultiJob = createMultiJobFromHtmlFile(setupMultiJobFile);

        if (!SINGLE_MULTIJOB_SETUP) {

            inlineMultiJob = createMultiJobFromHtmlFile(inlineMultiJobFile);
            teardownMultiJob = createMultiJobFromHtmlFile(teardownMultiJobFile);
            if (setupMultiJob.getNumber() < inlineMultiJob.getNumber() || inlineMultiJob.getNumber() < teardownMultiJob.getNumber()) {
                JOptionPane.showMessageDialog(frame, "Setup MultiJob number has to be greater than Inline MultiJob number,\n" +
                                "and (or) Inline MultiJob number has to greater than Teardown MultiJob number:\n\n" +
                                "Setup > Inline > Teardown",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


        // --- Create setupdoc and set lists and pages

        if (!SINGLE_MULTIJOB_SETUP) {
            setupDoc = new SetupDoc(setupMultiJob.getNumber(), inlineMultiJob.getNumber(), teardownMultiJob.getNumber());
            setupDoc.setTeardownMsdList(createTeardownMsdList(setupMultiJob, inlineMultiJob, teardownMultiJob));
            setupDoc.setTeardownMsdPage(createTeardownMsdPage(setupDoc.getTeardownMsdList(), teardownMultiJob.getNumber()));

            setupDoc.setMatchedList(createMatchedList(setupMultiJob, teardownMultiJob));
            setupDoc.setMatchedPage(createMatchedPage(setupDoc.getMatchedList(), setupMultiJob.getNumber(), teardownMultiJob.getNumber()));
        } else {
            setupDoc = new SetupDoc(setupMultiJob.getNumber());
        }

        createCartListsAndPages();

        setupDoc.setTrayList(createTrayList(setupMultiJob));
        setupDoc.setTrayPage(createTrayPage(setupDoc.getTrayList()));

        setupDoc.setExtraList(setupMultiJob.getGroup1());
        setupDoc.setExtraPage(createExtraPage(setupDoc.getExtraList()));

        setupDoc.setSetupMsdList(createSetupMsdList(setupMultiJob));
        setupDoc.setSetupMsdPage(createSetupMsdPage(setupDoc.getSetupMsdList()));

        // Create cover page with stats
        setupDoc.setCoverPage(createCoverPage(setupDoc));

        if (!SINGLE_MULTIJOB_SETUP) {
            String strElementTeardownMsd = elementToString(setupDoc.getTeardownMsdPage(), "div.page-break");
            String strElementMatched = elementToString(setupDoc.getMatchedPage(), "div.page-break");

            appendElement(strElementTeardownMsd);
            appendElement(strElementMatched);
        }

        // --- Create strings with HTML-code from every page
        String strElement1_1 = elementToString(setupDoc.getCart1_1Page(), "div.page-break");
        String strElement1_2 = elementToString(setupDoc.getCart1_2Page(), "div.page-break");
        String strElement2_1 = elementToString(setupDoc.getCart2_1Page(), "div.page-break");
        String strElement2_2 = elementToString(setupDoc.getCart2_2Page(), "div.page-break");
        String strElement3_1 = elementToString(setupDoc.getCart3_1Page(), "div.page-break");
        String strElement3_2 = elementToString(setupDoc.getCart3_2Page(), "div.page-break");
        String strElement4_1 = elementToString(setupDoc.getCart4_1Page(), "div.page-break");
        String strElement4_2 = elementToString(setupDoc.getCart4_2Page(), "div.page-break");

        String strElementTray = elementToString(setupDoc.getTrayPage(), "div.page-break");
        String strElementExtra = elementToString(setupDoc.getExtraPage(), "div.page-break");
        String strElementSetupMsd = elementToString(setupDoc.getSetupMsdPage(), "div.page-break");

        //Set output file name and title
        if (!SINGLE_MULTIJOB_SETUP) {
            OUTPUT_FILE_NAME = "MultiJob - " + setupDoc.getSetupMultiJobNumber() + " - " + setupDoc.getInlineMultiJobNumber() + " - " + setupDoc.getTeardownMultiJobNumber();
        } else {
            OUTPUT_FILE_NAME = "MultiJob - " + setupDoc.getSetupMultiJobNumber();
        }
        setupDoc.getCoverPage().title(OUTPUT_FILE_NAME);

        //Append all HTML-code into the cover page, which will hold all data.
        appendElement(strElement1_1);
        appendElement(strElement1_2);
        appendElement(strElement2_1);
        appendElement(strElement2_2);
        appendElement(strElement3_1);
        appendElement(strElement3_2);
        appendElement(strElement4_1);
        appendElement(strElement4_2);
        appendElement(strElementExtra);
        appendElement(strElementTray);
        appendElement(strElementSetupMsd);

        saveFile();
    }

    // --- Create multijob and corresponding tables and ArrayLists ---
    private MultiJob createMultiJobFromHtmlFile(File file) {
        try {
            //Read and parse HTML file from Nexim
            Document doc = Jsoup.parse(file, "UTF-8");

            // Select relevant table-elements.
            Elements tables = doc.select("table:contains(Position)");
            Element table0 = tables.first();

            //Create ArrayList for group0
            ArrayList<Component> group0 = new ArrayList<Component>();
            // Check if the given multijob contains more than one group, the "extra group" i.e "extra rullar"
            // Create multijobs and ArrayList for group1 if needed
            if (tables.size() == 1) {
                MultiJob multiJob = new MultiJob(file.getName(), false, table0, group0);
                pickDataCellsForGroup(table0, group0);
                return multiJob;
            } else if (tables.size() > 1) {
                Element table1 = tables.get(1);
                ArrayList<Component> group1 = new ArrayList<Component>();
                MultiJob multiJob = new MultiJob(file.getName(), true, table0, table1, group0, group1);
                pickDataCellsForGroup(table0, group0);
                pickDataCellsForGroup(table1, group1);
                return multiJob;
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create MultiJob objects" + "\n\nPlease contact support: \nAlbert Lännerström \n076-8416523 \nalbert.lannerstrom@gmail.com \n", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to create multijob-files");
            }

            // If failed to read
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not parse HTML-files correctly. The layout or code in the input files may have changed. \n" + ex.getMessage() + "\n\nPlease contact support: \nAlbert Lännerström \n076-8416523 \nalbert.lannerstrom@gmail.com \n", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //Pick out relevant elements from table
    private void pickDataCellsForGroup(Element table, ArrayList<Component> group) {
        for (Element row : table.select("tr")) {
            if (row.children().is("td")) {
                Elements dataCells = row.select("td:lt(8)");
                addComponentsToArrayList(dataCells, group);
            }
        }
        flagDuplicates(group);
    }
    //Add picked elements to ArrayList

    private void addComponentsToArrayList(Elements dataCells, ArrayList<Component> group) {
        String positionCell = dataCells.get(0).text();
        String resultCell = dataCells.get(1).text();
        String partNumberCell = dataCells.get(2).text();
        String partCommentCell = dataCells.get(3).text();
        String typeCell = dataCells.get(4).text();
        String widthCell = dataCells.get(5).text();
        String feederNameCell = dataCells.get(6).text();
        String qtyCell = dataCells.get(7).text();
        Component c = new Component(positionCell, resultCell, partNumberCell, partCommentCell, typeCell, widthCell, feederNameCell, qtyCell, false, false);
        group.add(c);
    }

    private void flagDuplicates(ArrayList<Component> group) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                if (group.get(i).getPartNumber().equals(group.get(j).getPartNumber())) {
                    group.get(i).setDuplicate(true);
                    group.get(j).setDuplicate(true);
                }
            }
        }
    }

    private Document getHtmlTemplate(String filePath) {
        try {
            File file = new File(filePath);
            Document doc = Jsoup.parse(file, "UTF-8");
            return doc;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not load HTML-template or \"" + filePath + "\" is missing.", "Could not load HTML-template", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        return null;
    }


    // --- Create a list for MSD components. Some to be saved in CABINET and some to be removed ---
    private ArrayList<Component> createTeardownMsdList(MultiJob newMultiJob, MultiJob currentMultiJob, MultiJob oldMultiJob) {
        ArrayList<Component> newMsdList = filterMsds(newMultiJob);
        ArrayList<Component> currentMsdList = filterMsds(currentMultiJob);
        ArrayList<Component> oldMsdList = filterMsds(oldMultiJob);

        ArrayList<Component> teardownMsdList = new ArrayList<Component>();

        for (Component oldComp : oldMsdList) {
            if (oldComp.getPartComment().contains(FAST_KEY)) {
                oldComp.setResult(CABINET_KEY);
            } else {
                for (Component newComp : newMsdList) {
                    if (!newComp.getPartComment().contains(FAST_KEY)) {
                        if (oldComp.getPartNumber().equals(newComp.getPartNumber())) {
                            oldComp.setResult(CABINET_KEY);
                            newComp.setResult(SAVED_CABINET_KEY);
                            break;
                        } else {
                            oldComp.setResult(STORAGE_KEY);
                        }
                    }
                }
                for (Component currComp : currentMsdList) {
                    if (!currComp.getPartComment().contains(FAST_KEY)) {
                        if (oldComp.getPartNumber().equals(currComp.getPartNumber())) {
                            oldComp.setResult(CABINET_KEY);
                            break;
                        } else if (!oldComp.getResult().equals(CABINET_KEY)) {
                            oldComp.setResult(STORAGE_KEY);
                        }
                    }
                }
            }
            teardownMsdList.add(oldComp);
        }
        return teardownMsdList;
    }

    // --- Filter out MSD components from list ---
    private ArrayList<Component> filterMsds(MultiJob multiJob) {
        ArrayList<Component> msdList = new ArrayList<Component>();
        for (Component c : multiJob.getGroup0()) {
            if (c.getPartComment().contains(MSD_KEY) && c.getPosition().length() < TRAY_LENGTH_KEY) {
                msdList.add(c);
            }
        }

        if (multiJob.hasExtraGroup()) {
            for (Component c : multiJob.getGroup1()) {
                if (c.getPartComment().contains(MSD_KEY)) {
                    msdList.add(c);
                }
            }
        }
        return msdList;
    }

    private Document createTeardownMsdPage(ArrayList<Component> teardownMsdList, int oldMultiJobNumber) {
        Document doc = getHtmlTemplate(TEARDOWNMSD_TEMPLATE_PATH);

        String title = "Teardown MSD list - from #" + oldMultiJobNumber;
        doc.select("div.t1").first().text(title);

        Element tbody = doc.select("tbody").first();
        for (Component c : teardownMsdList) {
            String result = c.getResult();
            if (c.isMatched()) {
                result = "Saved";
            }
            String other = "&nbsp;";
            if (c.isDuplicate()) {
                other = "Duplicate";
            }
            tbody.append("<tr>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + result + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + other + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");
        }
        doc.select("tr:contains(MSD)").attr("class", "msd");
        doc.select("tr:contains(MSD)").select("tr:contains(FAST)").attr("class", "fast-msd"); //"FAST" MSDs
        doc.select("tr:contains(Duplicate)").attr("class", "duplicate");
        doc.select("tr:contains(" + STORAGE_KEY + ")").select("tr:not(" + FAST_KEY + ")").attr("class", "msd2"); //MSDs to remove to storage
        return doc;
    }

    // --- Create a list for matched components to be saved ---
    private ArrayList<Component> createMatchedList(MultiJob newMultiJob, MultiJob oldMultiJob) {
        ArrayList<Component> matchedGroup = new ArrayList<Component>();
        int i = 0;
        for (Component cNew : newMultiJob.getGroup0()) {
            //Look for matching component number omitting "FASTMATARE", MSDs, tray components, and already matched components
            if (cNew.getPartComment().contains(FAST_KEY)
                    || cNew.getPartComment().contains(MSD_KEY)
                    || cNew.getPosition().length() > TRAY_LENGTH_KEY) {
                continue;
            } else {
                for (Component cOld : oldMultiJob.getGroup0()) {
                    if (cNew.getPartNumber().equals(cOld.getPartNumber())
                            && !cOld.isMatched()) {
                        cNew.setMatched(true);
                        cNew.setResult("Save");
                        cOld.setMatched(true);
                        cNew.setOldPosition(cOld.getPosition());
                        matchedGroup.add(cNew);
                        i++;
                        break;
                    }
                }

                if (oldMultiJob.hasExtraGroup())
                    for (Component cOld : oldMultiJob.getGroup1()) {
                        if (cNew.getPartNumber().equals(cOld.getPartNumber())
                                && !cOld.isMatched() && !cNew.isMatched()) {
                            cNew.setMatched(true);
                            cNew.setResult("Save");
                            cOld.setMatched(true);
                            cNew.setOldPosition(cOld.getPosition() + " / Extra");
                            matchedGroup.add(cNew);
                            i++;
                            break;
                        }
                    }
            }
        }
        MATCHED_AMOUNT = i;
        return matchedGroup;
    }

    private Document createMatchedPage(ArrayList<Component> matchedList, int newMultiJobNumber, int oldMultiJobNumber) {
        Document doc = getHtmlTemplate(MATCHEDLIST_TEMPLATE_PATH);

        String title = "Matched Components - from #" + oldMultiJobNumber + " to #" + newMultiJobNumber;
        doc.select("div.t1").first().text(title);

        Element tbody = doc.select("tbody").first();
        for (int i = 0; i < matchedList.size(); i++) {
            Component c = matchedList.get(i);
            tbody.append("<tr>" +
                    "<td>" + c.getOldPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + c.getResult() + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");
            int j = i + 1;
            if (j < matchedList.size()) {
                Component cNext = matchedList.get(j);
                if (!(c.getCartNumber().equals(cNext.getCartNumber()))) {
                    tbody.append("<tr>" +
                            "<td style=\"padding: 5px;\"></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "<td></td>" +
                            "</tr>");
                }
            }
        }

        doc.select("tr:contains(Save)").attr("class", "matched");

        return doc;
    }

    private void createCartListsAndPages() {
        COMPONENT_AMOUNT = 0;
        setupDoc.setCart1_1List(createCart("1 - 1"));
        setupDoc.setCart1_1Page(createCartPage(setupDoc.getCart1_1List(), "1 - 1"));

        setupDoc.setCart1_2List(createCart("1 - 2"));
        setupDoc.setCart1_2Page(createCartPage(setupDoc.getCart1_2List(), "1 - 2"));

        setupDoc.setCart2_1List(createCart("2 - 1"));
        setupDoc.setCart2_1Page(createCartPage(setupDoc.getCart2_1List(), "2 - 1"));

        setupDoc.setCart2_2List(createCart("2 - 2"));
        setupDoc.setCart2_2Page(createCartPage(setupDoc.getCart2_2List(), "2 - 2"));

        setupDoc.setCart3_1List(createCart("3 - 1"));
        setupDoc.setCart3_1Page(createCartPage(setupDoc.getCart3_1List(), "3 - 1"));

        setupDoc.setCart3_2List(createCart("3 - 2"));
        setupDoc.setCart3_2Page(createCartPage(setupDoc.getCart3_2List(), "3 - 2"));

        setupDoc.setCart4_1List(createCart("4 - 1"));
        setupDoc.setCart4_1Page(createCartPage(setupDoc.getCart4_1List(), "4 - 1"));

        setupDoc.setCart4_2List(createCart("4 - 2"));
        setupDoc.setCart4_2Page(createCartPage(setupDoc.getCart4_2List(), "4 - 2"));
    }

    // --- Create a cart by filtering out feeders matching the given cart name (1 - 1 for example)
    private ArrayList<Component> createCart(String cartName) {
        ArrayList<Component> cartList = new ArrayList<Component>();
        int i = 0;
        for (Component c : setupMultiJob.getGroup0()) {
            String position = c.getPosition();
            if (position.length() < TRAY_LENGTH_KEY) {
                String cartNumber = c.getCartNumber();
                if (cartNumber.equals(cartName)) {
                    cartList.add(c);
                    i++;
                }
            }
        }
        COMPONENT_AMOUNT += i;
        return cartList;
    }

    private Document createCartPage(ArrayList<Component> cartList, String cartName) {
        Document doc = getHtmlTemplate(CART_TEMPLATE_PATH);

        doc.select("div.t1").first().text(cartName);

        Element tbody = doc.select("tbody").first();
        for (Component c : cartList) {
            String result = c.getResult();
            if (c.isMatched()) {
                result = "Saved";
            }
            String other = "&nbsp;";
            if (c.isDuplicate()) {
                other = "Duplicate";
            }
            tbody.append("<tr>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + result + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + other + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");

            if (!c.getPartComment().equals("")) {
                doc.select("tr").last().attr("class", "comment");
            }

        }

        doc.select("tr:contains(FASTMATARE)").attr("class", "fast");
        doc.select("tr:contains(Duplicate)").attr("class", "duplicate");
        doc.select("tr:contains(Saved)").attr("class", "matched");
        doc.select("tr:contains(MSD)").attr("class", "msd2");
        doc.select("tr:contains(MSD)").select("tr:contains(FAST)").attr("class", "fast-msd"); //"FAST" MSDs
        doc.select("tr:contains(" + SAVED_CABINET_KEY + ")").select("tr:not(" + FAST_KEY + ")").attr("class", "msd"); //Saved MSDs
        doc.select("tr:contains(Use)").attr("class", "comment");
        doc.select("tr:contains(USE)").attr("class", "comment");

        return doc;
    }

    // ---
    private ArrayList<Component> createTrayList(MultiJob multiJob) {
        ArrayList<Component> trayList = new ArrayList<Component>();
        int i = 0;
        for (Component c : multiJob.getGroup0()) {
            String position = c.getPosition();
            if (position.length() > TRAY_LENGTH_KEY) {
                trayList.add(c);
                i++;
            }
        }

        if (multiJob.hasExtraGroup()) {
            for (Component c : multiJob.getGroup1()) {
                String position = c.getPosition();
                if (position.length() > TRAY_LENGTH_KEY) {
                    trayList.add(c);
                    i++;
                }
            }
        }
        TRAY_AMOUNT = i;
        COMPONENT_AMOUNT += i;
        return trayList;
    }

    private Document createTrayPage(ArrayList<Component> trayList) {
        Document doc = getHtmlTemplate(TRAY_TEMPLATE_PATH);

        Element tbody = doc.select("tbody").first();
        for (Component c : trayList) {
            if (c.getPosition().contains("B")) {
                tbody = doc.select("tbody").last();
            }
            String other = "&nbsp;";
            if (c.isDuplicate()) {
                other = "Duplicate";
            }
            tbody.append("<tr>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + c.getResult() + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + other + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");

            if (!c.getPartComment().equals("")) {
                doc.select("tr").last().attr("class", "comment");
            }

        }

        doc.select("tr:contains(MSD)").attr("class", "msd2"); //MSDs to get from storage
        doc.select("tr:contains(MSD)").select("tr:contains(FAST)").attr("class", "fast-msd"); //"FAST" MSDs
        doc.select("tr:contains(Duplicate)").attr("class", "duplicate");

        return doc;
    }

    private Document createExtraPage(ArrayList<Component> extraList) {
        if (extraList == null) {
            return null;
        }
        Document doc = getHtmlTemplate(EXTRA_TEMPLATE_PATH);

        Element tbody = doc.select("tbody").first();
        for (Component c : extraList) {
            String result = c.getResult();
            if (c.isMatched()) {
                result = "Saved";
            }
            String other = "&nbsp;";
            if (c.isDuplicate()) {
                other = "Duplicate";
            }
            tbody.append("<tr>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + result + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + other + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");

            if (!c.getPartComment().equals("")) {
                doc.select("tr").last().attr("class", "comment");
            }
        }

        doc.select("tr:contains(FASTMATARE)").attr("class", "fast");
        doc.select("tr:contains(Duplicate)").attr("class", "duplicate");
        doc.select("tr:contains(Saved)").attr("class", "matched");
        doc.select("tr:contains(MSD)").attr("class", "msd");

        return doc;
    }

    private ArrayList<Component> createSetupMsdList(MultiJob multiJob) {
        ArrayList<Component> setupMsdList = filterMsds(multiJob);
        ArrayList<Component> inlineMsdList = null;
        if (!SINGLE_MULTIJOB_SETUP) {
            inlineMsdList = filterMsds(inlineMultiJob);
        }
        ArrayList<Component> msdList = new ArrayList<Component>();
        int i = 0;
        for (Component setupC : setupMsdList) {
            if (inlineMsdList != null) {
                for (Component inlineC : inlineMsdList) {
                    if (!inlineC.getPartComment().contains(FAST_KEY)) {
                        if (inlineC.getPartNumber().equals(setupC.getPartNumber())) {
                            setupC.setResult(SAVED_CABINET_KEY);
                            break;
                        }
                    }
                }
            }
            msdList.add(setupC);
            i++;
        }

        MSD_AMOUNT = i;
        return msdList;
    }

    private Document createSetupMsdPage(ArrayList<Component> msdList) {
        Document doc = getHtmlTemplate(MSD_TEMPLATE_PATH);

        Element tbody = doc.select("tbody").first();
        for (Component c : msdList) {
            String result = c.getResult();
            String other = "&nbsp;";
            if (c.isDuplicate()) {
                other = "Duplicate";
            }
            tbody.append("<tr>" +
                    "<td>" + c.getPosition() + "</td>" +
                    "<td>" + c.getPartNumber() + "</td>" +
                    "<td>" + result + "" + "</td>" +
                    "<td>" + "&nbsp;" + "</td>" +
                    "<td>" + c.getPartComment() + "</td>" +
                    "<td>" + other + "</td>" +
                    "<td>" + c.getType() + "</td>" +
                    "<td align=\"right\">" + c.getWidth() + "</td>" +
                    "</tr>");

            if (!c.getPartComment().equals("")) {
                doc.select("tr").last().attr("class", "comment");
            }
        }

        doc.select("tr:contains(MSD)").attr("class", "msd2"); //MSDs to get from storage
        doc.select("tr:contains(MSD)").select("tr:contains(FAST)").attr("class", "fast-msd"); //"FAST" MSDs
        doc.select("tr:contains(Duplicate)").attr("class", "duplicate");
        doc.select("tr:contains(" + SAVED_CABINET_KEY + ")").select("tr:not(" + FAST_KEY + ")").attr("class", "msd"); //Saved MSDs

        return doc;
    }

    private void countDuplicates(MultiJob multiJob) {
        int i = 0;
        for (Component c : multiJob.getGroup0()) {
            if (c.isDuplicate()) {
                i++;
            }
        }
        if (multiJob.hasExtraGroup()) {
            for (Component c : multiJob.getGroup1()) {
                if (c.isDuplicate()) {
                    i++;
                }
            }
        }
        DUPLICATE_AMOUNT = i;
    }

    private void countFASTMATARE(MultiJob multiJob) {
        int i = 0;
        for (Component c : multiJob.getGroup0()) {
            if (c.getPartComment().contains("FASTMATARE")) {
                i++;
            }
        }
        if (multiJob.hasExtraGroup()) {
            for (Component c : multiJob.getGroup1()) {
                if (c.getPartComment().contains("FASTMATARE")) {
                    i++;
                }
            }
        }
        FASTMATARE_AMOUNT = i;
    }

    private void countExtra(MultiJob multiJob) {
        if (multiJob.hasExtraGroup()) {
            int i = 0;
            for (Component c : multiJob.getGroup1()) {
                i++;
            }
            EXTRA_AMOUNT = i;
            COMPONENT_AMOUNT += i;
        }
    }

    private Document createCoverPage(SetupDoc setupDoc) {
        Document doc = getHtmlTemplate(COVER_PAGE_PATH);

        String setupMultiJobNumber = "#" + Integer.toString(setupDoc.getSetupMultiJobNumber());
        doc.select("div.main-multi-job").first().text(setupMultiJobNumber);
        if (!SINGLE_MULTIJOB_SETUP) {
            String inlineMultiJobNumber = "#" + Integer.toString(setupDoc.getInlineMultiJobNumber());
            String teardownMultiJobNumber = "#" + Integer.toString(setupDoc.getTeardownMultiJobNumber());
            doc.select("td:contains(Inline) + td").first().text(inlineMultiJobNumber);
            doc.select("td:contains(Teardown) + td").first().text(teardownMultiJobNumber);
        } else {
            doc.select("td:contains(Inline) + td").first().text("N/A");
            doc.select("td:contains(Teardown) + td").first().text("N/A");
        }

        countFASTMATARE(setupMultiJob);
        countDuplicates(setupMultiJob);
        countExtra(setupMultiJob);

        doc.select("td:contains(MSDs in setup) + td").first().text(Integer.toString(MSD_AMOUNT));
        doc.select("td:contains(FASTMATARE) + td").first().text(Integer.toString(FASTMATARE_AMOUNT));
        doc.select("td:contains(Duplicates) + td").first().text(Integer.toString(DUPLICATE_AMOUNT));
        doc.select("td:contains(Trays) + td").first().text(Integer.toString(TRAY_AMOUNT));
        doc.select("td:contains(Group 1 (extra)) + td").first().text(Integer.toString(EXTRA_AMOUNT));
        doc.select("td:contains(Saved feeders) + td").first().text(Integer.toString(MATCHED_AMOUNT));
        doc.select("td:contains(Total components) + td").first().text(Integer.toString(COMPONENT_AMOUNT));

        return doc;
    }

    private String elementToString(Document page, String cssQuery) {
        if (page != null) {
            String strElement = page.select(cssQuery).first().toString();
            if (strElement.contains("td")) {
                return strElement;
            }
        }
        return null;
    }

    private void appendElement(String strElement) {
        if (strElement != null) {
            setupDoc.getCoverPage().select("div").first().append(strElement);
        }
    }

    private void saveFile() {
        //--- Load previously saved state (if i exists) ---
        try {
            File saveFile = new File("Support Files/SavePath.txt");
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            String line = null;
            if ((line = reader.readLine()) != null) {
                savePath = line;
            }
            reader.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not load previously saved file path! \"Support Files/SavePath.txt\" is missing and will be created on the next try", "Could not load file path", JOptionPane.WARNING_MESSAGE);
        }

        //--- Main code ---
        try {
            //Get path
            File path = new File(savePath);
            //Create file chooser
            JFileChooser fileSave = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".html, .htm", "htm", "html");
            fileSave.setCurrentDirectory(path);
            fileSave.setFileFilter(filter);
            fileSave.setSelectedFile(new File(savePath + "/" + OUTPUT_FILE_NAME + ".html"));

            // --- Sorting ---
            Action details = fileSave.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);

            //Make sure a file is chosen
            if (fileSave.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File outputFile = fileSave.getSelectedFile();

                // --- Save saved path to savePath class field ---
                try {
                    FileWriter writer = new FileWriter("Support Files/SavePath.txt");
                    writer.write(outputFile.getParent());
                    savePath = outputFile.getParent();
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Could not save file path!", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

                // --- Save the complete file.
                try {
                    //Write the complete HTML-file
                    FileWriter writer = new FileWriter(outputFile);
                    writer.write(setupDoc.getCoverPage().toString());
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "File saved in chosen folder! \n" + outputFile.getName(), "File saved", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Could not save file!", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not open file chooser dialog or could not locate path folder", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
