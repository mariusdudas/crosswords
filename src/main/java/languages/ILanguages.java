package languages;

public interface ILanguages {
	// File menu strings
	String getFileText();
	char getFileMnemonic();
	String getNewText();
	char getNewMnemonic();
	String getOpenText();
	char getOpenMnemonic();
	String getContinueText();
	char getContinueMnemonic();
	String getSaveText();
	char getSaveMnemonic();
	String getDeleteText();
	char getDeleteMnemonic();
	String getExitText();
	char getExitMnemonic();
	
	// Settings menu strings
	String getSettingsText();
	char getSettingsMnemonic();
	String getColorsText();
	char getColorsMnemonic();
	String getSquareBackgroundText();
	char getSquareBackgroundMnemonic();
	String getSquareOutlineText();
	char getSquareOutlineMnemonic();
	String getSelectedSquareColorText();
	char getSelectedSquareColorMnemonic();
	String getSquareTextColorText();
	char getSquareTextColorMnemonic();
	String getSquareNumberColor();
	char getSquareNumberColorMnemonic();
	String getBlancSquareColorText();
	char getBlancSquareColorMnemonic();
	String getDrawAreaColorText();
	char getDrawAreaColorMnemonic();
	String getSizeText();
	char getSizeMnemonic();
	String getWidthInSquaresText();
	char getWidthInSquaresMnemonic();
	String getHeightInSquaresText();
	char getHeightInSquaresMnemonic();
	String getLoadImageText();
	char getLoadImageMnemonic();
	String getSaveSettingsText();
	char getSaveSettingsMnemonic();
	String getLoadSettingsText();
	char getLoadSettingsMnemonic();
	String getDeleteSettingsText();
	char getDeleteSettingsMnemonic();
	
	// Help menu strings
	String getHelpText();
	char getHelpMnemonic();
	
	// Language menu strings
	String getLanguageText();
	char getLanguageMnemonic();
	String getLanguageItemText();
	char getLanguageItemMnemonic();
	
	String getVersionContent();
	String getInformationContent();
	String getLoadSettingsError01();
	String getNormalSquareText();
	String getSelectedSquareText();
	String getBlancSquareText();
	String getWidthText();
	String getHeightText();
	
	/*
	 *  Load settings section
	 */
	
	String getOKButtonText();
	char getOKButtonMnemonic();
	String getLoadSettingsDeleteButtonText();
	String getLoadSettingsCancelButtonText();
	String getLoadSettingsRenameButtonText();
	String getLoadSettingsRenameLabelText();
	String getYesRenameBtnText();
	String getNoRenameBtnText();
	String getDeletePreferenceText();
	String getDeletePreferenceNameText();
	
	/*
	 * Save settings section
	 */

	String getSaveSettingsNameLabelText();
	String getSaveSettingsDuplicateNameText();
	String getSaveSettingsYesButtonText();
	String getSaveSettingsNoButtonText();
	String getSaveSettingsIdenticalNameTitle();
	String getSaveSettingsIdenticalNameText();
	
	String getPreferenceName();
	String getSavePreferencesMessage();
	
	/*
	 * Edit area section
	 */
	
	String getDefinitionLabelText();
	String getSolutionLabelText();
	String getAutomaticNumberingText();
	String getNumberText();
	String getDirectionText();
	
	String getSaveButtonText();
	char getSaveButtonMnemonic();
	
	/*
	 * Popup menu section
	 */
	
	String getSetBlancText();
	char getSetBlancMnemonic();
	String getSetNonBlancText();
	char getSetNonBlancMnemonic();
	String getMultipleSelectionText();
	char getMultipleSelectionMnemonic();
	String getStopMultipleSelectionText();
	char getStopMultipleSelectionMnemonic();
	
	/*
	 * SpinnerListModel strings
	 */
	
	String getHorizontal();
	String getVertical();
	
	/*
	 * Database strings section
	 */
	
	String getGameId();
	String getGameName();
	String getGameTheme();
	String getGameDescription();
	String getGameSize();
	String getGameImage();
	String getGamePreferences();
	String getGameRating();
	String getLoadButtonText();
	char getLoadButtonMnemonic();
	String getCancelButtonText();
	char getCancelButtonMnemonic();
	String getOpenGameText();
	String getDeleteGameText();
	
	String getNoIncompleteGameText();
	String getContinueGameText();
	
	String getNameLabelText();
	String getThemeLabelText();
	String getSizeLabelText();
	String getDescriptionLabelText();
	String getPreferenceLabelText();
	String getImageLabelText();
	String getCompleteLabelText();
	String getSaveGameText();
	String getImageName();
}
