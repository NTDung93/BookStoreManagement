package dao;

import Database.FileHandler;
import dto.Publisher;
import utils.Color;
import utils.Input;
import utils.Notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PublisherManager {
    ArrayList<Publisher> listPublisher;
    Input input;
    Notification notification;

    FileHandler fileHandler;

    public PublisherManager() {
//        listPublisher = new ArrayList<>();
        fileHandler = new FileHandler();
        listPublisher = fileHandler.getListPublisherFile();
        input = new Input(listPublisher, null);
        notification = new Notification();
    }

    public ArrayList<Publisher> getListPublisher(){
        return listPublisher;
    }

    public void createNewPublisher() {
        Publisher newPublisher = input.createPublisher();
        listPublisher.add(newPublisher);
        notification.showSuccessNoti("Created new publisher successfully!");
//        fileHandler.saveListPublisherToFile(listPublisher);
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.createNewPublisher();
        } else {
            return;
        }
    }

    public void deletePublisher() {
        int index = input.inputPubIdGetIndex();
        if (index == -1) {
            notification.showErrorNoti("Publisher's id does not exist!");
            System.out.println();
            notification.showErrorNoti("Delete fail!");
        } else {
            Publisher publisher = listPublisher.get(index);
            listPublisher.remove(publisher);
            fileHandler.saveListPublisherToFile(listPublisher);
            notification.showSuccessNoti("Delete publisher " + publisher.getPubName() + " successfully!");
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.deletePublisher();
        }
    }

    public void savePubListToFile() {
        fileHandler.saveListPublisherToFile(listPublisher);
        notification.showSuccessNoti("Save the Publishers list to file successfully!");
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.savePubListToFile();
        }
    }



    public void printListPub() {
        if (listPublisher.size() == 0) {
            notification.showErrorNoti("List is empty now!");
        } else {
            Collections.sort(listPublisher, new Comparator<Publisher>() {
                @Override
                public int compare(Publisher pub1, Publisher pub2) {
                    return (pub1.getPubName().toLowerCase().compareTo(pub2.getPubName().toLowerCase()));
                }
            });
            System.out.printf("%1s %10s %20s %25s\t\n", Color.CYAN_BOLD_BRIGHT + "No", "Id", "Name", "Phone number" + Color.RESET);
            int ordinalNumber = 1;
            for (Publisher pub : listPublisher) {
                System.out.printf("%1s %15s %20s %19s\t\n", Color.PURPLE_BOLD_BRIGHT + ordinalNumber++, pub.getPubId(),pub.getPubName(), pub.getPubPhoneNumber() + Color.RESET);
            }
        }
    }
}
