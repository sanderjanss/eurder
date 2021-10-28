package com.switchfullywork.eurder.domain.order;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.item.ItemGroup;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {

    private final UUID customerId;
    private final List<CreateItemGroupDTO> listOfItemGroups;

    public CreateOrderDTO(UUID customerId, List<CreateItemGroupDTO> listOfItemGroups) {
        this.customerId = customerId;
        this.listOfItemGroups = listOfItemGroups;

    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<CreateItemGroupDTO> getListOfItemGroups() {
        return listOfItemGroups;
    }


    public static class CreateOrderDTOBuilder{

        private UUID customerId;
        private List<CreateItemGroupDTO> listOfItemGroups;


        public CreateOrderDTOBuilder setCustomerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public CreateOrderDTOBuilder setListOfItemGroups(List<CreateItemGroupDTO> listOfItemGroups) {
            this.listOfItemGroups = listOfItemGroups;
            return this;
        }

        public CreateOrderDTO build(){
            return new CreateOrderDTO(this.customerId, this.listOfItemGroups);
        }
    }
}
