package com.polysfactory.glassremote.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.google.glass.companion.Proto.Envelope;
import com.polysfactory.glassremote.model.GlassConnection;
import com.polysfactory.glassremote.util.GlassMessagingUtil;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 180;
    private GlassConnection mGlassConnection;

    public ControlPanel(GlassConnection glassConnection) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);

        mGlassConnection = glassConnection;

        final JTextArea textArea = new JTextArea(100, 20);
        textArea.setBounds(5, 5, 630, 110);
        Border border = BorderFactory.createLineBorder(Color.black);
        textArea.setBorder(border);
        add(textArea);

        JButton sendButton = new JButton("Send to Timeline");
        sendButton.setBounds(5, 120, 150, 40);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                Envelope envelope = GlassMessagingUtil.createTimelineMessage(text);
                mGlassConnection.write(envelope);
            }
        });
        add(sendButton);
    }
}
