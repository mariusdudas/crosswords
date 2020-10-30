package languages;

public class Romanian implements ILanguages {
	
	@Override
	public String getFileText() {
		return "Fisier";
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
		return "Continua";
	}

	@Override
	public char getContinueMnemonic() {
		return 'C';
	}

	@Override
	public String getSaveText() {
		return "Salveaza";
	}

	@Override
	public char getSaveMnemonic() {
		return 'S';
	}

	@Override
	public String getDeleteText() {
		return "Sterge";
	}

	@Override
	public char getDeleteMnemonic() {
		return 't';
	}

	@Override
	public String getExitText() {
		return "Iesire";
	}

	@Override
	public char getExitMnemonic() {
		return 's';
	}

	@Override
	public String getSettingsText() {
		return "Preferinte";
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
		return "Fundal patrat";
	}

	@Override
	public char getSquareBackgroundMnemonic() {
		return 'F';
	}

	@Override
	public String getSquareOutlineText() {
		return "Contur patrat";
	}

	@Override
	public char getSquareOutlineMnemonic() {
		return 'o';
	}

	@Override
	public String getSelectedSquareColorText() {
		return "Culoarea selectiei";
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
		return "Culoarea numerelor randurilor si coloanelor";
	}

	@Override
	public char getSquareNumberColorMnemonic() {
		return 'n';
	}

	@Override
	public String getBlancSquareColorText() {
		return "Culoarea patratului blanc";
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
		return "Marime";
	}

	@Override
	public char getSizeMnemonic() {
		return 'M';
	}

	@Override
	public String getWidthInSquaresText() {
		return "Latimea in patrate";
	}

	@Override
	public char getWidthInSquaresMnemonic() {
		return 'L';
	}

	@Override
	public String getHeightInSquaresText() {
		return "Inaltimea in patrate";
	}

	@Override
	public char getHeightInSquaresMnemonic() {
		return 'I';
	}

	@Override
	public String getSaveSettingsText() {
		return "Salvare preferinte";
	}

	@Override
	public char getSaveSettingsMnemonic() {
		return 'S';
	}

	@Override
	public String getLoadSettingsText() {
		return "Incarcare preferinte";
	}

	@Override
	public char getLoadSettingsMnemonic() {
		return 'i';
	}

	@Override
	public String getDeleteSettingsText() {
		return "Stergere preferinte";
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
		return "Engleza";
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
		return "Informatii";
	}

	@Override
	public String getLoadSettingsError01() {
		return "Nu exista preferinte salvate";
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
		return "Latime: ";
	}

	@Override
	public String getHeightText() {
		return "Inaltime: ";
	}

	@Override
	public String getPreferenceName() {
		return "Numele preferintei nu poate fi nul";
	}

	@Override
	public String getLoadImageText() {
		return "Incarca imagine";
	}

	@Override
	public char getLoadImageMnemonic() {
		return 'i';
	}
	
	@Override
	public String getSavePreferencesMessage() {
		return "Salveaza preferinta";
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
		return "Sterge";
	}

	@Override
	public String getLoadSettingsCancelButtonText() {
		return "Renunta";
	}

	@Override
	public String getLoadSettingsRenameButtonText() {
		return "Redenumeste";
	}

	@Override
	public String getLoadSettingsRenameLabelText() {
		return "Redenumeste preferinta";
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
	public String getDeletePreferenceText() {
		return "Sterge";
	}

	@Override
	public String getDeletePreferenceNameText() {
		return "Sterge preferinta selectata?";
	}

	@Override
	public String getSaveSettingsNameLabelText() {
		return "Introduceti numele preferintei";
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
		return "Nume deja folosit. Va rog alegeti altul";
	}

	public String getDefinitionLabelText() {
		return "Definitie";
	}

	public String getSolutionLabelText() {
		return "Solutie";
	}

	public String getAutomaticNumberingText() {
		return "Numerotare automata";
	}

	@Override
	public String getNumberText() {
		return "Numar";
	}

	@Override
	public String getSetBlancText() {
		return "Fa blanc";
	}
	
	@Override
	public char getSetBlancMnemonic() {
		return 'b';
	}

	@Override
	public String getSetNonBlancText() {
		return "Fa non-blanc";
	}

	@Override
	public char getSetNonBlancMnemonic() {
		return 'n';
	}
	
	@Override
	public String getMultipleSelectionText() {
		return "Selectie multipla";
	}

	@Override
	public char getMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getStopMultipleSelectionText() {
		return "Opreste selectia multipla";
	}

	@Override
	public char getStopMultipleSelectionMnemonic() {
		return 'm';
	}

	@Override
	public String getSaveButtonText() {
		return "Salveaza";
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
		return "Directie";
	}

	@Override
	public String getGameId() {
		return "Id";
	}
	
	@Override
	public String getGameName() {
		return "Nume";
	}

	@Override
	public String getGameTheme() {
		return "Tema";
	}

	@Override
	public String getGameDescription() {
		return "Descriere";
	}

	@Override
	public String getGameSize() {
		return "Marime";
	}

	@Override
	public String getGameImage() {
		return "Imagine";
	}

	@Override
	public String getGamePreferences() {
		return "Preferinte";
	}

	@Override
	public String getGameRating() {
		return "Apreciere";
	}

	@Override
	public String getLoadButtonText() {
		return "Incarca";
	}

	@Override
	public char getLoadButtonMnemonic() {
		return 'I';
	}

	@Override
	public String getCancelButtonText() {
		return "Renunta";
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
		return "Sterge joc";
	}

	@Override
	public String getNoIncompleteGameText() {
		return "Nu s-au gasit jocuri incomplete";
	}

	@Override
	public String getContinueGameText() {
		return "Continua ultimul joc neterminat";
	}

	@Override
	public String getNameLabelText() {
		return "Nume";
	}

	@Override
	public String getThemeLabelText() {
		return "Tema";
	}

	@Override
	public String getSizeLabelText() {
		return "Marime";
	}

	@Override
	public String getDescriptionLabelText() {
		return "Descriere";
	}

	@Override
	public String getPreferenceLabelText() {
		return "Preferinta";
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
