package de.yannicklem.shoppinglist.core.list.service;

import de.yannicklem.shoppinglist.core.list.entity.ShoppingList;
import de.yannicklem.shoppinglist.core.user.security.service.CurrentUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.stereotype.Service;


@Service
@RepositoryEventHandler(ShoppingList.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired ))
public class ShoppingListService {

    private final CurrentUserService currentUserService;
    private final ShoppingListValidationService shoppingListValidationService;

    @HandleBeforeCreate(ShoppingList.class)
    public void handleBeforeCreate(ShoppingList shoppingList) {

        if (shoppingList != null) {
            shoppingList.getOwners().add(currentUserService.getCurrentUser());
        }

        shoppingListValidationService.validate(shoppingList);
    }
}
