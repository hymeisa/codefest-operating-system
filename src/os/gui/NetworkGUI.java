package src.os.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkGUI extends JFrame implements Runnable {
    private JTextField urlField;
    private JButton fetchButton;
    private JTextArea infoArea;

    public NetworkGUI() {
        super("codefestOS Network");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation to dispose
        setSize(500, 400);
        setLocationRelativeTo(null);

        urlField = new JTextField();
        fetchButton = new JButton("Fetch Info");
        infoArea = new JTextArea();
        infoArea.setEditable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("URL: "), BorderLayout.WEST);
        inputPanel.add(urlField, BorderLayout.CENTER);
        inputPanel.add(fetchButton, BorderLayout.EAST);
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWebsiteInfo();
            }
        });
    }

    private void fetchWebsiteInfo() {
        String url = urlField.getText();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a URL.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String title = extractTitle(url);
            String[] paragraphs = extractParagraphs(url);

            infoArea.setText("Title: " + title + "\n\n");
            infoArea.append("Paragraphs:\n");
            for (String paragraph : paragraphs) {
                infoArea.append(paragraph + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching website information.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private String extractTitle(String url) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Response code: " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String htmlContent = response.toString();
            return extractTitleFromHtml(htmlContent);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private String extractTitleFromHtml(String htmlContent) {
        Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = titlePattern.matcher(htmlContent);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Title not found";
        }
    }

    private String[] extractParagraphs(String url) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Response code: " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String htmlContent = response.toString();
            return extractParagraphsFromHtml(htmlContent);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private String[] extractParagraphsFromHtml(String htmlContent) {
        Pattern paragraphPattern = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher = paragraphPattern.matcher(htmlContent);
        StringBuilder paragraphs = new StringBuilder();
        while (matcher.find()) {
            paragraphs.append(matcher.group(1)).append("\n");
        }
        return paragraphs.toString().split("\n");
    }

    //public static void main(String args[]) {
        //SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetworkGUI().setVisible(true);
            }
        //});
    //}
}