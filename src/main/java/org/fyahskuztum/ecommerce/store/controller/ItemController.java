package org.fyahskuztum.ecommerce.store.controller;

import jakarta.validation.Valid;
import org.fyahskuztum.ecommerce.store.model.Item;
import org.fyahskuztum.ecommerce.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/inventory")
    public String displayInventoryPage(Model model){
        System.out.println("===========>IN displayInventoryPage() ");
        List<Item> allItemList = itemService.getAllItems();
        model.addAttribute("itemList", allItemList);
        return "inventory";
    }

    @GetMapping("/inventory/create")
    public String createItemForm(Model model) {
        System.out.println("===========>IN createItemForm() ");
        model.addAttribute("item", new Item());
        return "newItemForm";
    }

    @PostMapping("/inventory")
    public String addNewItem(@ModelAttribute("item") @Valid Item item, BindingResult result) {
        System.out.println("===========>IN addNewItem() ");

        Optional<Item> existingItem = itemService.getItemByName(item.getName());

        if(existingItem.isPresent()) {
            result.rejectValue("name", null, "There is already an existing item with this item name");
        }

        if(result.hasErrors()) {
            return "newItemForm";
        }

        itemService.addNewItem(item);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory/edit/{itemId}")
    public String updateItemForm(Model model, @PathVariable Long itemId) {
        System.out.println("===========>IN updateItemForm() ");
        model.addAttribute("item", itemService.getItemById(itemId).get());
        return "editItemForm";
    }

    @PostMapping("/inventory/edit")
    public String updateItem(@ModelAttribute("item") @Valid Item updatedItem, BindingResult result) {
        System.out.println("===========>IN updateItem() ");

        Item existingItemById = itemService.getItemById(updatedItem.getId()).get();

        // if item name is changed - check if the new item name already exist
        if(!existingItemById.getName().equals(updatedItem.getName())) {
            Optional<Item> existingItemByName = itemService.getItemByName(updatedItem.getName());

            if(existingItemByName.isPresent()) {
                result.rejectValue("name", null, "There is already an existing item with this item name");
            }
        }

        if(result.hasErrors()) {
            return "editItemForm";
        }

        itemService.updateItem(updatedItem);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory/delete/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        System.out.println("===========>IN deleteItem() ");
        itemService.deleteItemById(itemId);
        return "redirect:/inventory";
    }

}