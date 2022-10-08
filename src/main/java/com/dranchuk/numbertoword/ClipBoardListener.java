package com.dranchuk.numbertoword;

import java.awt.*;
import java.awt.datatransfer.*;

import static com.dranchuk.numbertoword.ClipboardController.convertBuffer;

class ClipBoardListener extends Thread implements ClipboardOwner {
    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    boolean bEnough = true;

    @Override
    public void run() {
        Transferable trans = sysClip.getContents(this);
        regainOwnership(trans);
        System.out.println("Listening to board...");
        while (true) {
            if (isitEnough()) break;
        }
    }

    public void setEnough(boolean b) {
        bEnough = b;
    }

    boolean isitEnough() {
        return bEnough;
    }

    public void lostOwnership(Clipboard c, Transferable t) {
        try {
            sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Transferable contents = c.getContents(this); //EXCEPTION
            regainOwnership(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void processContents(Transferable t) {
        if (isitEnough()) return;
        DataFlavor[] flavors = t.getTransferDataFlavors();
        String result;
        for (int i = flavors.length - 1; i >= 0; i--) {
            try {
                Object o = t.getTransferData(flavors[i]);
                if (o instanceof String) {
                    System.out.println((String) o);
                    result = convertBuffer((String) o);
                    if (result != null) {
                        StringSelection stringSelection = new StringSelection(result);
                        sysClip.setContents(stringSelection, stringSelection);
                    }
                    break;
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    void regainOwnership(Transferable t) {
        sysClip.setContents(t, this);
        processContents(t);
    }

}
