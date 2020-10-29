package languages;

public class English implements ILanguages {
	
	public String FileText = "File";

	@Override
	public String getFileText() {
		return "File";
	}

	@Override
	public char getFileMnemonic() {
		return 'F';
	}

	@Override
	public String getNewText() {
		return "New";
	}

	@Override
	public char getNewMnemonic() {
		return 'N';
	}

	@Override
	public String getOpenText() {
		return "Open";
	}

	@Override
	public char getOpenMnemonic() {
		return 'O';
	}

	@Override
	public String getContinueText() {
		return "Continue";
	}

	@Override
	public char getContinueMnemonic() {
		return 'C';
	}

	@Override
	public String getSaveText() {
		return "Save";
	}

	@Override
	public char getSaveMnemonic() {
		return 'S';
	}

	@Override
	public String getDeleteText() {
		return "Delete";
	}

	@Override
	public char getDeleteMnemonic() {
		return 'd';
	}

	@Override
	public String getExitText() {
		return "Exit";
	}

	@Override
	public char getExitMnemonic() {
		return 'x';
	}

	@Override
	public String getSettingsText() {
		return "Settings";
	}

	@Override
	public char getSettingsMnemonic() {
		return 'S';
	}

	@Override
	public String getColorsText() {
		return "Colors";
	}

	@Override
	public char getColorsMnemonic() {
		return 'C';
	}

	@Override
	public String getSquareBackgroundText() {
		return "Square background";
	}

	@Override
	public char getSquareBackgroundMnemonic() {
		return 'b';
	}

	@Override
	public String getSquareOutlineText() {
		return "Square outline";
	}

	@Override
	public char getSquareOutlineMnemonic() {
		return 'o';
	}

	@Override
	public String getSelectedSquareColorText() {
		return "Square selection";
	}

	@Override
	public char getSelectedSquareColorMnemonic() {
		return 's';
	}

	@Override
	public String getSquareTextColorText() {
		return "Square text color";
	}

	@Override
	public char getSquareTextColorMnemonic() {
		return 'c';
	}
	
	@Override
	public String getSquareNumberColor() {
		return "Squares numbering color";
	}

	@Override
	public char getSquareNumberColorMnemonic() {
		return 'n';
	}
	@Override
	public String getBlancSquareColorText() {
		return "Blanc square";
	}

	@Override
	public char getBlancSquareColorMnemonic() {
		return 'l';
	}
	
	@Override
	public String getDrawAreaColorText() {
		return "Draw area";
	}

	@Override
	public char getDrawAreaColorMnemonic() {
		return 'd';
	}

	@Override
	public String getSizeText() {
		return "Size";
	}

	@Override
	public char getSizeMnemonic() {
		return 'S';
	}

	@Override
	public String getWidthInSquaresText() {
		return "Width in squares";
	}

	@Override
	public char getWidthInSquaresMnemonic() {
		return 'W';
	}

	@Override
	public String getHeightInSquaresText() {
		return "Height in squares";
	}

	@Override
	public char getHeightInSquaresMnemonic() {
		return 'H';
	}

	@Override
	public String getSaveSettingsText() {
		return "Save settings";
	}

	@Override
	public char getSaveSettingsMnemonic() {
		return 'a';
	}

	@Override
	public String getLoadSettingsText() {
		return "Load settings";
	}
	
	@Override
	public char getLoadSettingsMnemonic() {
		return 'l';
	}
	
	@Override
	public String getDeleteSettingsText() {
		return "Delete settings";
	}

	@Override
	public char getDeleteSettingsMnemonic() {
		return 'd';
	}

	@Override
	public String getHelpText() {
		return "Help";
	}

	@Override
	public char getHelpMnemonic() {
		return 'H';
	}

	@Override
	public String getLanguageText() {
		return "Language";
	}

	@Override
	public char getLanguageMnemonic() {
		return 'L';
	}

	@Override
	public String getLanguageItemText() {
		return "Romanian";
	}

	@Override
	public char getLanguageItemMnemonic() {
		return 'R';
	}

	@Override
	public String getVersionContent() {
		return "The program version is ...";
	}

	@Override
	public String getInformationContent() {
		return "Information";
	}

	@Override
	public String getLoadSettingsError01() {
		return "No saved settings";
	}

	@Override
	public String getNormalSquareText() {
		return "Normal";
	}

	@Override
	public String getSelectedSquareText() {
		return "Selected";
	}

	@Override
	public String getBlancSquareText() {
		return "Blanc";
	}

	@Override
	public String getWidthText() {
		return "Width: ";
	}

	@Override
	public String getHeightText() {
		return "Height: ";
	}

	@Override
	public String getPreferenceName() {
		return "Preference name cannot be empty";
	}

	@Override
	public String getLoadImageText() {
		return "Load image";
	}

	@Override
	public char getLoadImageMnemonic() {
		return 'i';
	}
	
	@Override
	public String getSavePreferencesMessage() {
		return "Save preference";
	}

	@Override
	public String getOKButtonText() {
		return "Ok";
	}

	public char getOKButtonMnemonic() {
		return 'o';
	}
	
	@Override
	public String getLoadSettingsDeleteButtonText() {
		return "Delete";
	}

	@Override
	public String getLoadSettingsCancelButtonText() {
		return "Cancel";
	}

	@Override
	public String getLoadSettingsRenameButtonText() {
		return "Rename";
	}

	@Override
	public String getLoadSettingsRenameLabelText() {
		return "Rename preference";
	}

	@Override
	public String getYesRenameBtnText() {
		return "Yes";
	}

	@Override
	public String getNoRenameBtnText() {
		return "No";
	}

	@Override
	public String getDeletePreferenceText() {
		return "Delete";
	}

	@Override
	public String getDeletePreferenceNameText() {
		return "Delete selected preference?";
	}

	@Override
	public String getSaveSettingsNameLabelText() {
		return "Enter settings name";
	}

	@Override
	public String getSaveSettingsDuplicateNameText() {
		return "Duplicate name";
	}

	@Override
	public String getSaveSettingsYesButtonText() {
		return "Yes";
	}

	@Override
	public String getSaveSettingsNoButtonText() {
		return "No";
	}

	@Override
	public String getSaveSettingsIdenticalNameTitle() {
		return "Identical name";
	}
	
	@Override
	public String getSaveSettingsIdenticalNameText() {
		return "Name already in use. Please select another";
	}

	public String getDefinitionLabelText() {
		return "Definition";
	}

	public String getSolutionLabelText() {
		return "Solution";
	}

	public String getAutomaticNumberingText() {
		return "Automatic numbering";
	}

	@Override
	public String getNumberText() {
		return "Number";
	}

	@Override
	public String getSetBlancText() {
		return "Set as blanc";
	}
	
	@Override
	public char getSetBlancMnemonic() {
		return 'b';
	}

	@Override
	public String getSetNonBlancText() {
		return "Set as non-blanc";
	}
	
	@Override
	public char getSetNonBlancMnemonic() {
		return 'n';
	}

	@Override
	public String getMultipleSelectionText() {
		return "Select multiple squares";
	}

	@Override
	public char getMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getStopMultipleSelectionText() {
		return "End multiple selection";
	}

	@Override
	public char getStopMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getSaveButtonText() {
		return "Save";
	}

	@Override
	public char getSaveButtonMnemonic() {
		return 'S';
	}

	@Override
	public String getHorizontal() {
		return "Horizontal";
	}

	@Override
	public String getVertical() {
		return "Vertical";
	}

	@Override
	public String getDirectionText() {
		return "Direction";
	}

	@Override
	public String getGameId() {
		return "Id";
	}
	
	@Override
	public String getGameName() {
		return "Name";
	}

	@Override
	public String getGameTheme() {
		return "Theme";
	}

	@Override
	public String getGameDescription() {
		return "Description";
	}

	@Override
	public String getGameSize() {
		return "Size";
	}

	@Override
	public String getGameImage() {
		return "Image";
	}

	@Override
	public String getGamePreferences() {
		return "Preferences";
	}

	@Override
	public String getGameRating() {
		return "Rating";
	}

	@Override
	public String getLoadButtonText() {
		return "Load";
	}

	@Override
	public char getLoadButtonMnemonic() {
		return 'L';
	}

	@Override
	public String getCancelButtonText() {
		return "Cancel";
	}

	@Override
	public char getCancelButtonMnemonic() {
		return 'C';
	}

	@Override
	public String getOpenGameText() {
		return "Open game";
	}

	@Override
	public String getNoIncompleteGameText() {
		return "No incomplete game found";
	}

	@Override
	public String getNoIncompleteGameButtonText() {
		return "Ok";
	}

	@Override
	public char getNoIncompleteGameButtonMnemonic() {
		return 'o';
	}

	@Override
	public String getContinueGameText() {
		return "Continue last unsaved game";
	}

	@Override
	public String getNameLabelText() {
		return "Name";
	}

	@Override
	public String getThemeLabelText() {
		return "Theme";
	}

	@Override
	public String getSizeLabelText() {
		return "Size";
	}

	@Override
	public String getDescriptionLabelText() {
		return "Description";
	}

	@Override
	public String getPreferenceLabelText() {
		return "Preference";
	}

	@Override
	public String getImageLabelText() {
		return "Image";
	}

	@Override
	public String getCompleteLabelText() {
		return "Complete";
	}

	@Override
	public String getSaveGameText() {
		return "Save game";
	}

	@Override
	public String getImageName() {
		return "Image name";
	}

}
