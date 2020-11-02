package languages;

public class Romanian implements ILanguages {
	
	@Override
	public String getFileText() {
		return "Fișier";
	}

	@Override
	public char getFileMnemonic() {
		return 'F';
	}

	@Override
	public String getNewText() {
		return "Nou";
	}

	@Override
	public char getNewMnemonic() {
		return 'N';
	}

	@Override
	public String getOpenText() {
		return "Deschide";
	}

	@Override
	public char getOpenMnemonic() {
		return 'D';
	}

	@Override
	public String getContinueText() {
		return "Continuă";
	}

	@Override
	public char getContinueMnemonic() {
		return 'C';
	}

	@Override
	public String getSaveText() {
		return "Salvează";
	}

	@Override
	public char getSaveMnemonic() {
		return 'S';
	}

	@Override
	public String getDeleteText() {
		return "Șterge";
	}

	@Override
	public char getDeleteMnemonic() {
		return 't';
	}

	@Override
	public String getExitText() {
		return "Ieșire";
	}

	@Override
	public char getExitMnemonic() {
		return 's';
	}

	@Override
	public String getSettingsText() {
		return "Preferințe";
	}

	@Override
	public char getSettingsMnemonic() {
		return 'P';
	}

	@Override
	public String getColorsText() {
		return "Culori";
	}

	@Override
	public char getColorsMnemonic() {
		return 'C';
	}

	@Override
	public String getSquareBackgroundText() {
		return "Fundal pătrat";
	}

	@Override
	public char getSquareBackgroundMnemonic() {
		return 'F';
	}

	@Override
	public String getSquareOutlineText() {
		return "Contur pătrat";
	}

	@Override
	public char getSquareOutlineMnemonic() {
		return 'o';
	}

	@Override
	public String getSelectedSquareColorText() {
		return "Culoarea selecției";
	}

	@Override
	public char getSelectedSquareColorMnemonic() {
		return 'e';
	}

	@Override
	public String getSquareTextColorText() {
		return "Culoarea textului";
	}

	@Override
	public char getSquareTextColorMnemonic() {
		return 't';
	}
	
	@Override
	public String getSquareNumberColor() {
		return "Culoarea numerelor rândurilor și coloanelor";
	}

	@Override
	public char getSquareNumberColorMnemonic() {
		return 'n';
	}

	@Override
	public String getBlancSquareColorText() {
		return "Culoarea pătratului blanc";
	}

	@Override
	public char getBlancSquareColorMnemonic() {
		return 'b';
	}
	
	@Override
	public String getDrawAreaColorText() {
		return "Zona de desen";
	}

	@Override
	public char getDrawAreaColorMnemonic() {
		return 'z';
	}

	@Override
	public String getSizeText() {
		return "Mărime";
	}

	@Override
	public char getSizeMnemonic() {
		return 'M';
	}

	@Override
	public String getWidthInSquaresText() {
		return "Lățimea în pătrate";
	}

	@Override
	public char getWidthInSquaresMnemonic() {
		return 'L';
	}

	@Override
	public String getHeightInSquaresText() {
		return "Înălțimea în pătrate";
	}

	@Override
	public char getHeightInSquaresMnemonic() {
		return 'Î';
	}

	@Override
	public String getSaveSettingsText() {
		return "Salvare preferințe";
	}

	@Override
	public char getSaveSettingsMnemonic() {
		return 'S';
	}

	@Override
	public String getLoadSettingsText() {
		return "Încărcare preferințe";
	}

	@Override
	public char getLoadSettingsMnemonic() {
		return 'i';
	}

	@Override
	public String getDeleteSettingsText() {
		return "Ștergere preferințe";
	}

	@Override
	public char getDeleteSettingsMnemonic() {
		return 't';
	}

	@Override
	public String getHelpText() {
		return "Ajutor";
	}

	@Override
	public char getHelpMnemonic() {
		return 'A';
	}

	@Override
	public String getLanguageText() {
		return "Limba";
	}

	@Override
	public char getLanguageMnemonic() {
		return 'L';
	}

	@Override
	public String getLanguageItemText() {
		return "Engleză";
	}

	@Override
	public char getLanguageItemMnemonic() {
		return 'E';
	}

	@Override
	public String getVersionContent() {
		return "Versiunea programului este ...";
	}

	@Override
	public String getInformationContent() {
		return "Informații";
	}

	@Override
	public String getLoadSettingsError01() {
		return "Nu există preferințe salvate";
	}

	@Override
	public String getNormalSquareText() {
		return "Normal";
	}

	@Override
	public String getSelectedSquareText() {
		return "Selectat";
	}

	@Override
	public String getBlancSquareText() {
		return "Blanc";
	}

	@Override
	public String getWidthText() {
		return "Lățime: ";
	}

	@Override
	public String getHeightText() {
		return "Înălțime: ";
	}

	@Override
	public String getPreferenceName() {
		return "Numele preferinței nu poate fi nul";
	}

	@Override
	public String getLoadImageText() {
		return "Încarcă imagine";
	}

	@Override
	public char getLoadImageMnemonic() {
		return 'i';
	}
	
	@Override
	public String getSavePreferencesMessage() {
		return "Salvează preferința";
	}

	@Override
	public String getOKButtonText() {
		return "Ok";
	}
	
	public char getOKButtonMnemonic() {
		return 'o';
	}

	@Override
	public String getLoadSettingsCancelButtonText() {
		return "Renunță";
	}

	@Override
	public String getLoadSettingsRenameButtonText() {
		return "Redenumește";
	}

	@Override
	public String getLoadSettingsRenameLabelText() {
		return "Redenumește preferința";
	}

	@Override
	public String getYesRenameBtnText() {
		return "Da";
	}

	@Override
	public String getNoRenameBtnText() {
		return "Nu";
	}

	@Override
	public String getDeletePreferenceNameText() {
		return "Șterge preferința selectată?";
	}

	@Override
	public String getSaveSettingsNameLabelText() {
		return "Introduceți numele preferinței";
	}

	@Override
	public String getSaveSettingsDuplicateNameText() {
		return "Nume duplicat";
	}

	@Override
	public String getSaveSettingsYesButtonText() {
		return "Da";
	}

	@Override
	public String getSaveSettingsNoButtonText() {
		return "Nu";
	}

	@Override
	public String getSaveSettingsIdenticalNameTitle() {
		return "Nume identic";
	}
	
	@Override
	public String getSaveSettingsIdenticalNameText() {
		return "Nume deja folosit. Vă rog alegeți altul";
	}

	public String getDefinitionLabelText() {
		return "Definiție";
	}

	public String getSolutionLabelText() {
		return "Soluție";
	}

	public String getAutomaticNumberingText() {
		return "Numerotare automată";
	}

	@Override
	public String getNumberText() {
		return "Număr";
	}

	@Override
	public String getSetBlancText() {
		return "Fă blanc";
	}
	
	@Override
	public char getSetBlancMnemonic() {
		return 'b';
	}

	@Override
	public String getSetNonBlancText() {
		return "Fă non-blanc";
	}

	@Override
	public char getSetNonBlancMnemonic() {
		return 'n';
	}
	
	@Override
	public String getMultipleSelectionText() {
		return "Selecție multiplă";
	}

	@Override
	public char getMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getStopMultipleSelectionText() {
		return "Oprește selecția multiplă";
	}

	@Override
	public char getStopMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getSaveButtonText() {
		return "Adaugă";
	}

	@Override
	public char getSaveButtonMnemonic() {
		return 'S';
	}

	@Override
	public String getHorizontal() {
		return "Orizontal";
	}

	@Override
	public String getVertical() {
		return "Vertical";
	}

	@Override
	public String getDirectionText() {
		return "Direcție";
	}

	@Override
	public String getGameId() {
		return "Id";
	}
	
	@Override
	public String getGameNameText() {
		return "Nume";
	}

	@Override
	public String getGameThemeText() {
		return "Temă";
	}

	@Override
	public String getGameDescriptionText() {
		return "Descriere";
	}

	@Override
	public String getGameSize() {
		return "Mărime";
	}

	@Override
	public String getGameImage() {
		return "Imagine";
	}

	@Override
	public String getGamePreferences() {
		return "Preferințe";
	}

	@Override
	public String getGameRating() {
		return "Apreciere";
	}

	@Override
	public String getLoadButtonText() {
		return "Încarcă";
	}

	@Override
	public char getLoadButtonMnemonic() {
		return 'Î';
	}

	@Override
	public String getCancelButtonText() {
		return "Renunță";
	}

	@Override
	public char getCancelButtonMnemonic() {
		return 'R';
	}

	@Override
	public String getOpenGameText() {
		return "Deschide joc";
	}
	
	public String getDeleteGameText() {
		return "Șterge joc";
	}

	@Override
	public String getNoIncompleteGameText() {
		return "Nu s-au găsit jocuri incomplete";
	}

	@Override
	public String getContinueGameText() {
		return "Continuă ultimul joc neterminat";
	}

	@Override
	public String getNameLabelText() {
		return "Nume";
	}

	@Override
	public String getSizeLabelText() {
		return "Mărime";
	}

	@Override
	public String getDescriptionLabelText() {
		return "Descriere";
	}

	@Override
	public String getPreferenceLabelText() {
		return "Preferință";
	}

	@Override
	public String getImageLabelText() {
		return "Imagine";
	}

	@Override
	public String getCompleteLabelText() {
		return "Complet";
	}

	@Override
	public String getSaveGameText() {
		return "Salvare joc";
	}

	@Override
	public String getImageName() {
		return "Numele imaginii";
	}

}
