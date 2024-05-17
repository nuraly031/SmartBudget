package kz.nuraly.budget.builders;

import kz.nuraly.budget.repositories.entities.AssetEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class AssetEntityBuilder {
    private UUID id;
    private BigDecimal amount;

    public AssetEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AssetEntityBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AssetEntity build() {
        var entity = new AssetEntity();
        entity.setAmount(this.amount);
        entity.setId(this.id);
        return entity;
    }
}
