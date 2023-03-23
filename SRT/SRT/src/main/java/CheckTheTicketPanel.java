import models.ControlCenter;
import models.Reservation;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CheckTheTicketPanel extends JPanel {
    private JPanel titlePanel;
    private JPanel areaInformationPanel;
    private JPanel trainInformationPanel;
    private JPanel paymentPanel;
    private JTextField dayTextField;
    private JLabel srtNameLabel;
    private JLabel seatNumberLabel;


    CheckTheTicketPanel(ControlCenter controlCenter,Reservation reservation,ArrayList<String> saveList) throws IOException, ClassNotFoundException {
        loadPaymentInformation(saveList);

        setLayout(new GridLayout(4, 1));

        panelSets();

        titleLabel();

        areaInformation(controlCenter, reservation);

        trainInformation();

        payment(controlCenter);

    }

    private void panelSets() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 2));
        this.add(titlePanel);

        areaInformationPanel = new JPanel();
        areaInformationPanel.setLayout(new GridLayout(2, 3));
        this.add(areaInformationPanel);

        trainInformationPanel = new JPanel();
        trainInformationPanel.setLayout(new GridLayout(2, 2));
        this.add(trainInformationPanel);

        paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(2, 2));
        this.add(paymentPanel);
    }

    private void titleLabel() {
        JTextField titleTextField = new JTextField("예약내역");
        titleTextField.setEditable(false);
        titlePanel.add(titleTextField);
    }

    private void areaInformation(ControlCenter controlCenter, Reservation reservation) {
        JTextField departureTextField = new JTextField("수서");
        departureTextField.setEditable(false);
        areaInformationPanel.add(departureTextField);

        areaInformationPanel.add(new JLabel());

        JTextField arriveTextField = new JTextField(controlCenter.getRegionName());
        arriveTextField.setEditable(false);
        areaInformationPanel.add(arriveTextField);

        JTextField departureTimeTextField = new JTextField();
        departureTimeTextField.setEditable(false);
        areaInformationPanel.add(departureTimeTextField);

        areaInformationPanel.add(new JLabel());

        JTextField arriveTimeTextField = new JTextField(reservation.getArriveHour() + " : " + reservation.getArriveMinute());
        arriveTimeTextField.setEditable(false);
        areaInformationPanel.add(arriveTimeTextField);
    }

    private void trainInformation() {
        dayTextField = new JTextField("2023년 3월");//TODO- srtday
        dayTextField.setEditable(false);
        trainInformationPanel.add(dayTextField);
        srtNameLabel = new JLabel("srt이름");
        trainInformationPanel.add(srtNameLabel);//TODO- srtname
        seatNumberLabel = new JLabel("seatNumber");
        trainInformationPanel.add(seatNumberLabel);//TODO- seatNumber
    }

    private void payment(ControlCenter controlCenter) {
        paymentPanel.add(new JLabel("총 결제금액: "));
//        paymentPanel.add(new JLabel(controlCenter.getPriceSum()));
        JButton priceButton = new JButton(String.valueOf(controlCenter.getPriceSum()));
        priceButton.setEnabled(false);
        paymentPanel.add(priceButton);
    }

    public void loadPaymentInformation(ArrayList<String> saveList) throws IOException, ClassNotFoundException {
        File file = new File("seatsInformation.csv");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            saveList = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
    }

}
