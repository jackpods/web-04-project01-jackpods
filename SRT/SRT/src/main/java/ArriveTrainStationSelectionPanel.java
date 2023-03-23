import models.ControlCenter;
import models.Region; //TODO 왜 models를 해야하지?

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArriveTrainStationSelectionPanel extends JPanel {

    private JPanel whereToGoDisplayPanel;
    private JPanel regionListPanel;
    private JPanel chooseButtonPanel;
    private JTextField arriveTextField;

    ArriveTrainStationSelectionPanel(ArrayList<Region> regionList, ControlCenter controlCenter) {
        setLayout(new GridLayout(3, 1));

        panelSets();

        whereToGoDisplay(controlCenter);

        regionList(regionList, controlCenter);
    }

    private void panelSets() {
        whereToGoDisplayPanel = new JPanel();
        whereToGoDisplayPanel.setLayout(new GridLayout(2, 3));
        this.add(whereToGoDisplayPanel);

        regionListPanel = new JPanel();
        this.add(regionListPanel);

        chooseButtonPanel = new JPanel();
        chooseButtonPanel.setLayout(new GridLayout(2, 3));
        this.add(chooseButtonPanel);
    }

    private void whereToGoDisplay(ControlCenter controlCenter) {
        whereToGoDisplayPanel.add(new JLabel("       출발"));
        whereToGoDisplayPanel.add(new JLabel());
        whereToGoDisplayPanel.add(new JLabel("       도착"));
        JTextField departureTextField = new JTextField("수서");//출발지
        departureTextField.setEditable(false);
        whereToGoDisplayPanel.add(departureTextField);
        JButton switchButton = new JButton("switch");
        whereToGoDisplayPanel.add(switchButton);
        arriveTextField = new JTextField(controlCenter.getRegionName());//도착지
        arriveTextField.setEditable(false);
        whereToGoDisplayPanel.add(arriveTextField);
    }

    private void regionList(ArrayList<Region> regionList, ControlCenter controlCenter) {
        JButton suseoButton = new JButton("수서");
        chooseButtonPanel.add(suseoButton);

        JButton dongtanButton = new JButton("동탄");
        dongtanButton.addActionListener(event -> {
                arriveTextField.setText("동탄");
                controlCenter.resetPrice();
                controlCenter.transfer(regionList.get(1).getDistance());
                controlCenter.send(regionList.get(1).getRegion());
        });
        chooseButtonPanel.add(dongtanButton);

        JButton pyeongtaekButton = new JButton("평택");
        pyeongtaekButton.addActionListener(event -> {
                arriveTextField.setText("평택");
                controlCenter.resetPrice();
                controlCenter.transfer(regionList.get(2).getDistance());
                controlCenter.send(regionList.get(2).getRegion());
                System.out.println("평택"+controlCenter.getPrice());
        });
        chooseButtonPanel.add(pyeongtaekButton);

        JButton cheonanButton = new JButton("천안");
        cheonanButton.addActionListener(event -> {
            arriveTextField.setText("천안");
            controlCenter.resetPrice();
            controlCenter.transfer(regionList.get(3).getDistance());
            controlCenter.send(regionList.get(3).getRegion());
            cheonanButton.setEnabled(false);
        });
        chooseButtonPanel.add(cheonanButton);

        JButton osongButton = new JButton("오송");
        osongButton.addActionListener(event -> {
            arriveTextField.setText("오송");
            controlCenter.resetPrice();
            controlCenter.transfer(regionList.get(4).getDistance());
            controlCenter.send(regionList.get(4).getRegion());
        });
        chooseButtonPanel.add(osongButton);

        JButton daejeonButton = new JButton("대전");
        daejeonButton.addActionListener(event -> {
            arriveTextField.setText("대전");
            controlCenter.resetPrice();
            controlCenter.transfer(regionList.get(5).getDistance());
            controlCenter.send(regionList.get(5).getRegion());

        });
        chooseButtonPanel.add(daejeonButton);
    }
}
