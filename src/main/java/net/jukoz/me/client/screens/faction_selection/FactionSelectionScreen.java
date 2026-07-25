package net.jukoz.me.client.screens.faction_selection;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.client.screens.utils.CycledSelectionButtonType;
import net.jukoz.me.client.screens.utils.widgets.*;
import net.jukoz.me.client.screens.utils.widgets.map.FactionSelectionMapWidget;
import net.jukoz.me.client.screens.utils.widgets.text.TextAlignment;
import net.jukoz.me.client.screens.utils.widgets.text.TextBlockWidget;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.data.BannerData;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.event.KeyEvent;
import java.util.*;

@Environment(EnvType.CLIENT)
public class FactionSelectionScreen extends Screen {
    private static final ResourceLocation FACTION_SELECTION_UI = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/faction_selection.png");
    private static final ResourceLocation FACTION_SELECTION_BANNER_UI = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/faction_selection_banner.png");
    private static final ResourceLocation FACTION_SELECTION_BUTTONS = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/faction_selection_buttons.png");
    private static final ResourceLocation MAP_SELECTION = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,"textures/gui/faction_selection_map.png");
    private static final Component FACTION_SELECTION_TITLE = Component.translatable("screen.me.faction_selection_screen");
    private static final int MINIMAL_MARGIN = 4;
    private FactionSelectionController controller;
    private AbstractClientPlayer player;
    private ModelPart bannerField;
    private SearchBarWidget searchBarWidget;
    private PlayableNpcPreviewWidget playableNpcPreviewWidget;
    private CycledSelectionWidget dispositionSelectionWidget;
    private CycledSelectionWidget factionSelectionWidget;
    private CycledSelectionWidget subfactionSelectionWidget;
    public Button factionRandomizerButton;
    public TextBlockWidget raceListTextBlockWidget;
    public TextBlockWidget factionDescriptionTextBlockWidget;

    // Map buttons
    public Button mapZoomInButton;
    public Button mapZoomOutButton;
    public Button mapFocusButton;
    public FactionSelectionMapWidget mapWidget;
    private CycledSelectionWidget raceCycledSelection;
    private CycledSelectionWidget spawnPointCycledSelection;
    public Button spawnSelectionRandomizerButton;
    public Button spawnSelectionConfirmButton;
    private float initialDelay;
    public FactionSelectionScreen(float delay) {
        super(FACTION_SELECTION_TITLE);
        this.initialDelay = delay;
        ModWidget.enableFocus(false);
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.bannerField = this.minecraft.getEntityModels().bakeLayer(ModelLayers.BANNER).getChild("flag");
        Entity cameraEntity = this.minecraft.getCameraEntity();
        if (cameraEntity instanceof AbstractClientPlayer abstractClientPlayerEntity) {
            this.player = abstractClientPlayerEntity;
            controller = new FactionSelectionController(this, player, initialDelay);
        } else {
            LoggerUtil.logError("FactionSelectionScreen::Init:Couldn't find player");
        }

        // Initialize Buttons
        // Search bar
        searchBarWidget = new SearchBarWidget(controller.getSearchBarPool(player.level()), controller);
        addRenderableWidget(searchBarWidget.getSearchBarToggleButton());
        for(Button widget : searchBarWidget.getAllButtons())
            addRenderableWidget(widget);

        // NpcPreview
        playableNpcPreviewWidget = new PlayableNpcPreviewWidget();

        addFactionSelectionPanelButtons();
        mapWidget = new FactionSelectionMapWidget(controller, 114, 114);
        mapWidget.selectSpawn(controller.getCurrentSpawnIndex());
        mapWidget.updateSelectedSpawn(controller.getCurrentSpawnIndex());
        addMapPanelButtonsAndWidgets();
        addRenderableWidget(searchBarWidget.getScreenClickButton());
    }

    /**
     * Add all faction selection buttons
     * - Cycled widgets & Randomizer
     */
    private void addFactionSelectionPanelButtons() {
        // Disposition
        dispositionSelectionWidget = new CycledSelectionWidget(
                button -> {
                    controller.dispositionUpdate(false);
                    updateEquipment();
                },
                button -> {
                    controller.dispositionUpdate(true);
                    updateEquipment();
                },
                null,
                CycledSelectionButtonType.GOLD);
        for(Button button: dispositionSelectionWidget.getButtons()){
            addRenderableWidget(button);
        }

        // Faction
        factionSelectionWidget = new CycledSelectionWidget(
                button -> {
                    controller.factionUpdate(false);
                    updateEquipment();
                },
                button -> {
                    controller.factionUpdate(true);
                    updateEquipment();
                },
                null,
                CycledSelectionButtonType.SILVER);
        for(Button button: factionSelectionWidget.getButtons()){
            addRenderableWidget(button);
        }

        // Subfaction
        subfactionSelectionWidget = new CycledSelectionWidget(
                button -> {
                    controller.subfactionUpdate(false);
                    updateEquipment();
                },
                button -> {
                    controller.subfactionUpdate(true);
                    updateEquipment();
                },
                null,
                CycledSelectionButtonType.NORMAL);

        for(Button button: subfactionSelectionWidget.getButtons()){
            addRenderableWidget(button);
        }

        for(Button button: playableNpcPreviewWidget.getButtons()){
            addRenderableWidget(button);
        }
        // Faction Randomizer
        factionRandomizerButton = Button.builder(
                Component.translatable("screen.me.button.faction_randomizer"),
                button -> {
                    controller.randomizeFaction(5);
                    updateEquipment();
                }).build();
        addRenderableWidget(factionRandomizerButton);
    }

    /**
     * Add all map buttons
     * - Map widgets & Cycled widgets & Randomizer & Confirms
     */
    private void addMapPanelButtonsAndWidgets() {
        for(Button button: mapWidget.getButtons()){
            addRenderableWidget(button);
        }

        // Focus current spawn point (from data)
        mapFocusButton = Button.builder(
                Component.translatable("screen.me.button.focus_current"),
                button -> {
                    controller.toggleMapFocus();
                    controller.setSpawnIndex(controller.getCurrentSpawnIndex());
                    mapWidget.addCooldown();
                }).build();
        addRenderableWidget(mapFocusButton);

        // Zoom out the map to have a more broad view
        mapZoomOutButton = Button.builder(
                Component.translatable("screen.me.button.zoom_out"),
                button -> {
                    mapWidget.dezoomClick();
                }).build();
        addRenderableWidget(mapZoomOutButton);

        // Zoom into the map to have a closeup view
        mapZoomInButton = Button.builder(
                Component.translatable("screen.me.button.zoom_in"),
                button -> {
                    mapWidget.zoomClick();
                }).build();
        addRenderableWidget(mapZoomInButton);

        // Race Selection
        raceCycledSelection = new CycledSelectionWidget(
                button -> {
                    controller.raceIndexUpdate(false);
                    updateEquipment();
                },
                button -> {
                    controller.raceIndexUpdate(true);
                    updateEquipment();
                },
                null,
                CycledSelectionButtonType.NORMAL);
        for(Button button: raceCycledSelection.getButtons()){
            addRenderableWidget(button);
        }

        // Spawn Point Selection
        spawnPointCycledSelection = new CycledSelectionWidget(
                button -> {
                    controller.spawnIndexUpdate(false);
                },
                button -> {
                    controller.spawnIndexUpdate(true);
                },
                null,
                CycledSelectionButtonType.NORMAL);

        for(Button button: spawnPointCycledSelection.getButtons()){
            addRenderableWidget(button);
        }

        // Random spawn selection
        spawnSelectionRandomizerButton = Button.builder(
                Component.translatable("screen.me.button.spawn_randomizer"),
                button -> {
                    controller.randomizeSpawn(5);
                }).build();
        addRenderableWidget(spawnSelectionRandomizerButton);

        // Confirm spawn selection
        spawnSelectionConfirmButton = Button.builder(
                Component.translatable("screen.me.button.confirm"),
                button -> {
                    controller.confirmSpawnSelection(player);
                }).build();
        addRenderableWidget(spawnSelectionConfirmButton);
        if(!controller.canConfirm())
            spawnSelectionConfirmButton.active = false;
    }

    public void updateEquipment(){
        if(player == null || controller == null) return;

        Faction faction = controller.getCurrentlySelectedFaction();

        if(faction != null)
            playableNpcPreviewWidget.updateEntity(controller.getCurrentPreview(player.level()), controller.getCurrentRace(), player.level());
        else
            playableNpcPreviewWidget.updateToDefaultEntity(player.level());
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        ModWidget.updateMouse(mouseX, mouseY);
        this.renderBackground(context, mouseX, mouseY, delta);
        this.drawPanels(context);
    }

    @Override
    public void tick() {
        if(controller != null)
            controller.reduceDelay(1f / 20);
        super.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Keybind : Escape || Other Escape
        if (keyCode == 256) {
            this.onClose();
            return true;
        }
        // Keybind : Tabulation
        if(keyCode == KeyEvent.VK_CODE_INPUT && !ModWidget.getFocusEnabled()){
            ModWidget.enableFocus(true);
            return true;
        }
        if(playableNpcPreviewWidget.keyPressed(keyCode, scanCode, modifiers)){
            return true;
        }
        if(searchBarWidget.keyPressed(keyCode, scanCode, modifiers)){
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        playableNpcPreviewWidget.keyReleased(keyCode, scanCode, modifiers);
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    protected void drawPanels(GuiGraphics context){
        int mainPanelWidth = 169;
        int mainPanelHeight = 207;

        drawFactionSelectionPanel(context, mainPanelWidth, mainPanelHeight);
        drawInformationPanel(context, mainPanelWidth, mainPanelHeight);
        drawMapPanel(context, mainPanelWidth, mainPanelHeight);
    }

    private void drawInformationPanel(GuiGraphics context, int mainPanelWidth, int mainPanelHeight) {
        int startX = (int) ((context.guiWidth() / 2f) - (mainPanelWidth / 2f));
        int startY = (int) ((context.guiHeight() / 2f) - (mainPanelHeight / 2f));

        context.blit(FACTION_SELECTION_UI,
                startX,
                startY,
                0, 0,
                mainPanelWidth,
                mainPanelHeight
        );

        int textStartY = startY + (MINIMAL_MARGIN * 2);
        int centerWithBanner = ((startX + (MINIMAL_MARGIN / 2)) + ((mainPanelWidth - 50) / 2));

        Component factionName =  controller.getCurrentFaction().tryGetShortName().withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_GRAY);
        int factionNameStartX = centerWithBanner - (font.width(factionName) / 2);
        context.drawString(font, factionName,
                factionNameStartX,
                textStartY, 0, false);
        if(isMouseOver(factionNameStartX, font.width(factionName), textStartY, font.lineHeight)){
            context.renderComponentTooltip(font, List.of(controller.getCurrentFaction().getFullName()), ModWidget.getMouseX(), ModWidget.getMouseY());
        }


        Faction faction = controller.getCurrentlySelectedFaction();
        if(faction != null){
            Faction subfaction = controller.getCurrentSubfaction();
            if(subfaction != null){
                textStartY += font.lineHeight + MINIMAL_MARGIN;
                Component dispositionText = Component.translatable("screen.me.information.subfaction");
                context.drawString(font, dispositionText,
                        startX + (MINIMAL_MARGIN),
                        textStartY, 0, false);

                context.drawString(font, subfaction.getFullName(),
                        startX + (MINIMAL_MARGIN) + font.width(dispositionText),
                        textStartY, 0, false);
            }
            List<Race> races = faction.getRaces(player.level());
            if(races != null || !races.isEmpty()){
                textStartY += font.lineHeight + MINIMAL_MARGIN;
                context.drawString(minecraft.font, Component.translatable((races.size() <= 1) ? "screen.me.information.races" : "screen.me.information.races.many").withStyle(ChatFormatting.UNDERLINE),
                        startX + MINIMAL_MARGIN,
                        textStartY, 0, false);

                if(raceListTextBlockWidget == null){
                    raceListTextBlockWidget = new TextBlockWidget(
                            startX + MINIMAL_MARGIN, textStartY + font.lineHeight + MINIMAL_MARGIN, mainPanelWidth - 50 - MINIMAL_MARGIN - (MINIMAL_MARGIN / 2), (font.lineHeight * 2) + MINIMAL_MARGIN
                    ).setAlignment(TextAlignment.LEFT);
                    raceListTextBlockWidget.setText(controller.getRaceListText());
                }
                raceListTextBlockWidget.setStartX(startX + MINIMAL_MARGIN).setStartY(textStartY + font.lineHeight + MINIMAL_MARGIN);
                raceListTextBlockWidget.draw(context, false, false);
                textStartY += (font.lineHeight * 2) + MINIMAL_MARGIN;
            }
        }

        if(factionDescriptionTextBlockWidget == null){
            factionDescriptionTextBlockWidget = new TextBlockWidget(
                    startX + MINIMAL_MARGIN, startY + 95, mainPanelWidth - (MINIMAL_MARGIN * 2) - 1, font.lineHeight * 10
            ).setAlignment(TextAlignment.LEFT);
            factionDescriptionTextBlockWidget.setText(controller.getCurrentFactionDescriptions());
        }

        int loreTextStart = startY + 95;

        context.drawString(minecraft.font, Component.translatable("screen.me.information.description").withStyle(ChatFormatting.UNDERLINE),
                startX + MINIMAL_MARGIN,
                loreTextStart - font.lineHeight - MINIMAL_MARGIN, 0, false);


        List<Component> texts = controller.getCurrentFactionDescriptions();
        factionDescriptionTextBlockWidget.setStartX(startX + MINIMAL_MARGIN).setStartY(startY + 95);
        factionDescriptionTextBlockWidget.draw(context, false, false);

        drawFactionBanner(context, startX + mainPanelWidth - 50, startY + 6);
    }

    public void reassignTexts(List<Component> races, List<Component> descriptions){
        if(raceListTextBlockWidget != null)
            raceListTextBlockWidget.setText(races);
        if(factionDescriptionTextBlockWidget != null)
            factionDescriptionTextBlockWidget.setText(descriptions);
    }

    private void drawFactionSelectionPanel(GuiGraphics context, int mainPanelWidth, int mainPanelHeight) {
        int endX = (int) ((context.guiWidth() / 2f) - (mainPanelWidth / 2f) - MINIMAL_MARGIN);
        int startX = Math.max(MINIMAL_MARGIN, endX  - mainPanelWidth);
        int startY = (int) ((context.guiHeight() / 2f) - (mainPanelHeight / 2f));

        // Draw disposition option
        Disposition disposition = controller.getCurrentDisposition();
        Faction faction = controller.getCurrentFaction();
        Faction subFaction = controller.getCurrentSubfaction();

        int centerX = endX - CycledSelectionWidget.TOTAL_WIDTH / 2;
        int endY = (int) ((context.guiHeight() / 2f) - (mainPanelHeight / 2f)) + mainPanelHeight;

        if(!playableNpcPreviewWidget.haveBeenInitialized)
            updateEquipment();



        int newStartY = startY + searchBarWidget.drawSearchBarCentered(context, centerX, startY, font);

        if(!searchBarWidget.searchIsToggled()){
            // Rendered first to be in the background
            playableNpcPreviewWidget.drawCenteredAnchoredBottom(context, centerX, endY - 18 - (MINIMAL_MARGIN * 2));
            drawFactionRandomizer(context, centerX, endY);
        }

        // List all widgets one after the other
        searchBarWidget.setEndY(endY);

        if(searchBarWidget.searchIsToggled()) {
            dispositionSelectionWidget.enableArrows(false);
            factionSelectionWidget.enableArrows(false);
            subfactionSelectionWidget.enableArrows(false);
            factionRandomizerButton.active = false;
            searchBarWidget.drawSearchResultsCentered(context, centerX, startY); // Todo : only give what's necessary/need to be showcased
            return;
        }

        // Disposition
        dispositionSelectionWidget.enableArrows(Disposition.values().length > 1);
        newStartY += MINIMAL_MARGIN + dispositionSelectionWidget.drawAnchored(context, endX, newStartY, false, disposition.getName(), font);

        // Faction
        int currentFactionCountForDisposition = controller.getCurrentDispositionFactionCount();
        factionSelectionWidget.enableArrows(currentFactionCountForDisposition > 1);
        if(faction != null){
            newStartY += MINIMAL_MARGIN + factionSelectionWidget.drawAnchored(context, endX, newStartY, false, (faction == null) ? null : faction.tryGetShortName(), font);

            // Subfaction
            subfactionSelectionWidget.enableArrows(controller.haveSubfaction() && (faction.getSubFactions() != null && faction.getSubFactions().size() > 1));
            if(controller.haveSubfaction())
                subfactionSelectionWidget.drawAnchored(context, endX, newStartY, false, (subFaction == null) ? null : subFaction.tryGetShortName(), font);
        }

        if(!factionRandomizerButton.active)
            factionRandomizerButton.active = true;
    }


    private boolean isMouseOver(int startX, int sizeX, int startY, int sizeY) {
        return ModWidget.isMouseOver(sizeX, sizeY, startX, startY);
    }

    protected void drawFactionRandomizer(GuiGraphics context, int centerX, int endY) {
        if(factionRandomizerButton == null) return;

        int sizeX = 52;
        int sizeY = 18;
        int startX = (int) (centerX - (sizeX / 2f));
        int startY = endY - sizeY;
        boolean mouseOver = isMouseOver(startX, sizeX, startY, sizeY);
        context.blit(FACTION_SELECTION_BUTTONS,
                startX,
                startY,
                103, (factionRandomizerButton.isFocused() || mouseOver) ? 92 : 74,
                sizeX,
                sizeY
        );
        factionRandomizerButton.setRectangle(sizeX, sizeY, startX, startY);
        if(ModWidget.getFocusEnabled() && factionRandomizerButton.isFocused()){
            context.blit(FACTION_SELECTION_BUTTONS,
                    startX,
                    startY,
                    103, 148,
                    sizeX,
                    sizeY
            );
        }
    }


    private void drawMapPanel(GuiGraphics context, int mainPanelWidth, int mainPanelHeight) {
        int startX = (int) ((context.guiWidth() / 2f) + (mainPanelWidth / 2f)) + MINIMAL_MARGIN;
        int startY = (int) ((context.guiHeight() / 2f) - (mainPanelHeight / 2f));

        int mapBackgroundWidth = 124;
        int mapBackgroundHeight = 124;

        context.blit(MAP_SELECTION,
                startX, startY,
                0, 0,
                mapBackgroundWidth,
                mapBackgroundHeight
        );

        mapWidget.drawAnchored(context,startX + 5, startY + 5, true);
        if(controller.mapFocusToggle != mapWidget.haveForcedMapTarget())
            controller.toggleMapFocus();

        // Arbritary
        int buttonStartX = startX + MINIMAL_MARGIN + 2;
        int buttonSize = 10;
        startY += mapBackgroundHeight;

        int smallButtonsStartY = startY - buttonSize - MINIMAL_MARGIN - 2;

        // Focus current
        context.blit(MAP_SELECTION,
                buttonStartX, smallButtonsStartY,
                235, (controller.mapFocusToggle) ? 20 : mapFocusButton.isFocused() || isMouseOver(buttonStartX, buttonSize, smallButtonsStartY, buttonSize) ? 10 : 0,
                buttonSize,
                buttonSize
        );
        mapFocusButton.setRectangle(buttonSize, buttonSize, buttonStartX, smallButtonsStartY);
        if(mapFocusButton.isFocused() && ModWidget.getFocusEnabled()){
            highlightedFocusMapButton(context, buttonStartX - 1, smallButtonsStartY - 1);
        }

        // Zoom in
        buttonStartX = startX + mapBackgroundWidth - MINIMAL_MARGIN - buttonSize - 2;
        boolean canZoomIn = mapWidget.canZoomIn();
        mapZoomInButton.active = canZoomIn;
        context.blit(MAP_SELECTION,
                buttonStartX, smallButtonsStartY,
                224, !canZoomIn ? 20 : mapZoomInButton.isFocused() || isMouseOver(buttonStartX, buttonSize, smallButtonsStartY, buttonSize) ? 10 : 0,
                buttonSize,
                buttonSize
        );
        mapZoomInButton.setRectangle(buttonSize, buttonSize, buttonStartX, smallButtonsStartY);
        if(canZoomIn && mapZoomInButton.isFocused() && ModWidget.getFocusEnabled()){
            highlightedFocusMapButton(context, buttonStartX - 1, smallButtonsStartY - 1);
        }

        // Zoom out
        buttonStartX -= buttonSize + MINIMAL_MARGIN;
        boolean canZoomOut = mapWidget.canZoomOut();
        mapZoomOutButton.active = canZoomOut;
        context.blit(MAP_SELECTION,
                buttonStartX, smallButtonsStartY,
                213, !canZoomOut ? 20 : mapZoomOutButton.isFocused() || isMouseOver(buttonStartX, buttonSize, smallButtonsStartY, buttonSize) ? 10 : 0,
                buttonSize,
                buttonSize
        );
        mapZoomOutButton.setRectangle(buttonSize, buttonSize, buttonStartX, smallButtonsStartY);
        if(canZoomOut && mapZoomOutButton.isFocused() && ModWidget.getFocusEnabled()){
            highlightedFocusMapButton(context, buttonStartX - 1, smallButtonsStartY - 1);
        }

        // Race option
        startY += MINIMAL_MARGIN;
        spawnPointCycledSelection.drawAnchored(context, startX,  startY,true, Component.translatable(controller.getCurrentSpawnKey()), font);
        spawnPointCycledSelection.enableArrows(controller.haveManySpawns());

        // Spawn point option
        startY += MINIMAL_MARGIN + CycledSelectionWidget.TOTAL_HEIGHT;
        raceCycledSelection.drawAnchored(context, startX,  startY,true, Component.translatable(controller.getCurrentRaceKey()), font);
        raceCycledSelection.enableArrows(controller.haveManyRaces());
        if(isMouseOver(startX, CycledSelectionWidget.TOTAL_WIDTH, startY, CycledSelectionWidget.TOTAL_HEIGHT)){
            Race race = controller.getCurrentRace();
            if(race != null){
                race.drawTooltip(player, context, font, ModWidget.getMouseX(), ModWidget.getMouseY());
            }
        }
        // Draw selection option
        int sizeX = 52;
        int sizeY = 18;
        buttonStartX = (int)(startX + (mapBackgroundWidth / 2f)) - (sizeX + MINIMAL_MARGIN);
        int buttonStartY = (int)((context.guiHeight() / 2f) - (mainPanelHeight / 2f)) + mainPanelHeight - sizeY;

        boolean mouseOver = isMouseOver(buttonStartX, sizeX, buttonStartY, sizeY);
        context.blit(FACTION_SELECTION_BUTTONS,
                buttonStartX,
                buttonStartY,
                103, spawnSelectionRandomizerButton.isFocused() || mouseOver ? 129 : 111,
                sizeX,
                sizeY
        );
        spawnSelectionRandomizerButton.setRectangle(sizeX, sizeY, buttonStartX, buttonStartY);
        if(ModWidget.getFocusEnabled() && spawnSelectionRandomizerButton.isFocused()){
            context.blit(FACTION_SELECTION_BUTTONS,
                    buttonStartX,
                    buttonStartY,
                    103, 148,
                    sizeX,
                    sizeY
            );
        }

        buttonStartX = (int)(startX + (mapBackgroundWidth / 2f)) + MINIMAL_MARGIN;
        mouseOver = isMouseOver(buttonStartX, sizeX, buttonStartY, sizeY);
        if(spawnSelectionConfirmButton.active){
            context.blit(FACTION_SELECTION_BUTTONS,
                    buttonStartX,
                    buttonStartY,
                    103, spawnSelectionConfirmButton.isFocused() || mouseOver ? 37 : 19,
                    sizeX,
                    sizeY
            );
        } else {
            context.blit(FACTION_SELECTION_BUTTONS,
                    buttonStartX,
                    buttonStartY,
                    156, 55,
                    sizeX,
                    sizeY
            );
            Component delayText = Component.literal(String.valueOf(controller.getDelayRounded()));
            context.drawString(font, delayText,
                    buttonStartX + (sizeX / 2) - (font.width(delayText) / 2),
                    buttonStartY + 5, 0xc4343e, true);
        }


        spawnSelectionConfirmButton.setRectangle(sizeX, sizeY, buttonStartX, buttonStartY);
        if(ModWidget.getFocusEnabled() && spawnSelectionConfirmButton.isFocused()){
            context.blit(FACTION_SELECTION_BUTTONS,
                    buttonStartX,
                    buttonStartY,
                    103, 148,
                    sizeX,
                    sizeY
            );
        }
    }

    public void enableConfirm(){
        if(spawnSelectionConfirmButton != null)
            spawnSelectionConfirmButton.active = true;
    }

    private void highlightedFocusMapButton(GuiGraphics context, int startX, int startY){
        context.blit(MAP_SELECTION,
                startX,
                startY,
                200, 0,
                12,
                12
        );
    }

    private void drawFactionBanner(GuiGraphics context, float startX, float startY){
        Lighting.setupForFlatItems();

        float size = 32f;

        float x = startX;
        float y = startY;

        int borderMarginX = 2;
        int borderMarginY = 2;

        // Positioning
        PoseStack matrixStack = new PoseStack();
        matrixStack.translate(x + borderMarginX + (size / 2f) + 4, y + borderMarginY, 1f);
        matrixStack.pushPose();
        matrixStack.scale(-size, size, 0.1f);
        this.bannerField.xRot = 0.0F;


        // Banner creation
        Faction faction = controller.getCurrentlySelectedFaction();
        if(faction == null) return;

        DyeColor color = faction.getBaseBannerColor();
        List<BannerData.BannerPatternWithColor> patterns = faction.getBannerPatternsWithColors(this.minecraft.level);
        if(patterns == null || patterns.isEmpty()) {
            LoggerUtil.logError("FactionSelectionScreen::drawFactionBanner - Cannot create banner because values are empty or null");
            return;
        }

        var bannerPatternRegistry = this.minecraft.level.registryAccess().registryOrThrow(Registries.BANNER_PATTERN);

        BannerPatternLayers.Builder bannerBuilder = new BannerPatternLayers.Builder();
        for(BannerData.BannerPatternWithColor entry : patterns){
            if(entry == null) continue;
            Holder<BannerPattern> pattern = bannerPatternRegistry.wrapAsHolder(entry.pattern);
            bannerBuilder.add(pattern, entry.color);
        }

        BannerRenderer.renderPatterns(matrixStack, context.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, this.bannerField, ModelBakery.BANNER_BASE, true, color, bannerBuilder.build());
        matrixStack.popPose();
        context.flush();
        Lighting.setupFor3DItems();

        context.blit(FACTION_SELECTION_BANNER_UI,
                (int) x - 2,
                (int) y - 2,
                0, 0,
                48,
                112
        );
    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        mapWidget.mouseClicked(mouseX, mouseY, button);
        playableNpcPreviewWidget.mouseClicked(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        mapWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        searchBarWidget.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        playableNpcPreviewWidget.mouseReleased(mouseX, mouseY, button);
        mapWidget.mouseReleased(mouseX, mouseY, button);
        searchBarWidget.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        mapWidget.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        searchBarWidget.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        searchBarWidget.charTyped(chr, modifiers);
        return super.charTyped(chr, modifiers);
    }
}