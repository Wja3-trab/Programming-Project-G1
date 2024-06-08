package com.medialab.rental;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int itemID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_user_id")
    private User lastUserID;
    @Column(name = "quantity_available")
    private int availableQuantity;
    @Column(name = "name")
    private String nameItem;
    @Column(name = "description")
    private String descriptionItem;

    public int getItemID() {
        return this.itemID;
    }

    public int getLastUserID() {
        return lastUserID.getUserID();
    }


    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    @Override
    public String toString() {
        return "Item => {\n" +
                "ID:\t" + getItemID() +
                "Name:\t" + getNameItem() +
                "Description:\t" + getDescriptionItem() +
                "ID of Last User:\t" + getLastUserID() +
                "Available Quantity:\t" + getAvailableQuantity() +
                "\n}";
    }
}
