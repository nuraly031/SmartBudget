package kz.nuraly.budget.controllers;

import kz.nuraly.budget.services.AssetsService;
import kz.nuraly.budget.services.dtos.AssetDto;
import kz.nuraly.budget.services.dtos.AssetsDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    private final AssetsService assetsService;

    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @GetMapping
    public AssetsDto getAssets() {
        return assetsService.getAllAssets();
    }

    @PostMapping
    public void setAsset(@RequestBody AssetDto dto) {
        assetsService.setAsset(dto);
    }

    @DeleteMapping
    public void deleteAsset(@RequestBody AssetDto dto) {
        assetsService.deleteAsset(dto);
    }
}
