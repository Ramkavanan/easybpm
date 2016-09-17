if(!form) var form = {};
if(!form.error) form.error = {};
if(!form.label) form.label = {};
if(!form.title) form.title = {};
if(!form.msg) form.msg = {};

if(!table) var table = {};
if(!table.error) table.error = {};
if(!table.label) table.label = {};
if(!table.title) table.title = {};
if(!table.msg) table.msg = {};


/*form.error.empty = "The Form should not be blank.";
form.error.emptyname = "The name should not be blank.";
form.error.specialcharacter = "The special characters are not allow in name.";
form.error.namespace = "The space is not allow in name.";
form.error.charwidthnumber ="The character width should be a number.";
form.error.maxcharnumber ="The maximum characters should be a number.";
form.error.emptytable = "The table should not be blank.";
form.error.emptycolumn = "The column should not be blank.";
form.error.rowfieldtype = "The row is should be a number.";
form.error.sizefieldtype = "The size should be a number.";
form.error.emptybuttonname = "The button text should not be blank.";
form.error.buttonlabelsize = "The button label should be a below 16 character.";
form.error.emptytablename = "Table Name should not be empty.";
form.error.emptyformname = "Form Name should not be empty.";
form.error.existsTableName = "Choose the existing table.";
form.error.formexists = "Form Name already exists!";
form.error.specialcharformname= "Form Name should contain only alphabets and numerics.";
form.error.fieldname = "Field name: ";
form.error.fieldnotmappedwithtable = " is not mapped with the table.";
form.error.emptylistview = "The List view name should not be blank.";
form.error.emptygridtitle = "The List view title should not be blank.";
form.error.emptyvaluefield = "The Value field should not be blank.";*/

form.error.empty = "Form should not be blank.";
form.error.emptyname = "Name should not be blank.";
form.error.specialcharacter = "Special characters are not allowed in name.";
form.error.namespace = "Space is not allowed in name.";
form.error.charwidthnumber ="Character Width accepts only numeric values.";
form.error.maxcharnumber ="Maximum Characters accepts only numeric values.";
form.error.emptytable = "Table should not be blank.";
form.error.emptycolumn = "Column Value should not be blank.";
form.error.columnValueShouldBeNumber = "Column Value should be a number."
form.error.rowfieldtype = "Row accepts only numeric values.";
form.error.sizefieldtype = "Size accepts only numeric values.";
form.error.emptybuttonname = "Button Text should not be blank.";
form.error.buttonlabelsize = "Button Label length should be less than 16 characters.";
form.error.emptytablename = "Table Name should not be empty.";
form.error.emptyformname = "Form Name should not be empty.";
form.error.existsTableName = "Choose the existing table.";
form.error.formexists = "Form Name already exists.";
form.error.specialcharformname= "Form Name should contain only alphabets and numerics.";
form.error.fieldname = "Field name: ";
form.error.fieldnotmappedwithtable = " is not mapped with the table.";
form.error.emptylistview = "List View Name should not be blank.";
form.error.emptygridtitle = "List View Title should not be blank.";
form.error.emptyvaluefield = "Value should not be blank.";

form.label.onclick = "onClick";
form.label.onblur = "onBlur";
form.label.onfocus = "onFocus";
form.label.onchange = "onChange";
form.label.selectType = "Select Type";
form.label.inputType = "Input Type"
form.label.table = "Table";
form.label.column = "Column";
form.label.inputtype = "Input Type";
form.label.availableoptions ="Available Options";
form.label.multipleselections = "Allow multiple selections";
form.label.style = "Style";
form.label.clickevent = "Click Event";
form.label.size = "Size";
form.label.id = "Id";
form.label.selecttype = "Select Type";
form.label.formnames = "Form Names";
form.label.listviewname = "List View Name";
form.label.isRequired = 'Is Required?';

form.label.className = "Class Name";
form.label.dataDictionary = "Data Dictionary";
form.label.gridtitle = "Title";
form.label.eventAndStyle = "Events and Style";
form.label.events = "Events";

form.label.onsubmit = 'JavaScript Event : On Submit';
form.label.onsubmitJava = 'Java Event : On Submit';
form.label.onload = 'onLoad';
form.label.onUnLoad = 'onUnLoad';
form.label.onclick = "onClick";
form.label.onblur = "onBlur";
form.label.onfocus = "onFocus";
form.label.onchange = "onChange";
form.label.ondblclick = "onDblClick";
form.label.onkeypress = "onKeyPress";
form.label.onselect = "onSelect";
form.title.selectProcess = "Select Process"
form.title.unselectProcess = "Unselect Process"
form.title.tableRelation = "Table Relation (* ref Parent Table)";
form.title.validation = 'Validation';
form.title.message = "Message"
form.title.deleteNews = "Delete News"
form.title.confirm = "Confirm";
form.title.deleteNotice = "Delete Notice"
form.title.check = "Check";
form.title.exportOption = "Export Option"
form.title.error = "Error"
form.title.success = "Success"
form.title.numberfield = 'Number Field Properties';
form.title.datetimepicker = 'Date Time Picker Properties';
form.title.allbuttons = 'All Buttons';
form.title.fileupload = 'File Upload Properties';
form.title.usercontrol = 'User Control';
form.title.subForms = 'Sub Forms';
form.title.richtextbox = 'Rich Textbox Properties';
form.title.gridview = 'Grid View Properties';

form.title.getformdetails = 'Specify Form Details, before proceeding';
form.title.formname = "Form Name";
form.title.tablename = "Table Name";
form.title.moduleName = "Module Name";
form.title.ispublic = "Is Public";
form.title.isTemplate = "Template";
form.title.isDefault = "Default";
form.title.autoid = "Auto Id";
form.title.description = "Description";

form.msg.differentformname = "Give a different Form Name.";
form.msg.addtoformbuttonconfirm = "Do you want to add this button in form buttons?";
form.msg.addtoformandbuttonmanager = "Add to this form and button manager";
form.msg.updatetoformandbuttonmanager = "Update to this form and button manager";
form.msg.addtoform = "Add to this form only";
form.msg.fileupload = "Column can support only following data types (longblob, longtext,varchar).";
form.msg.textfield = "Column can support only following data types (varchar, tinytext, text).";
form.msg.textarea = "Column can support only following data types (varchar, tinytext, text, bolb, tinyblob, mediumtext, longblob, longtext).";
form.msg.numberfield = "If input type is Number the column can support only following data types (numeric, int) or input type is Decimal (float, double, decimal).";
form.msg.selectbox = "Column can support only following data types (varchar, tinytext, text).";
form.msg.radiobutton = "Column can support only following data types (varchar, tinytext).";
form.msg.checkbox = "Column can support only following data types (varchar, tinytext).";
form.msg.hiddenfield = "Column can support only following data types (varchar, tinytext, text).";
form.msg.datetimepicker = "If input type is Date Picker the column can support only following data type (date) or input type is Date Time Picker (datetime).";
form.msg.userlist = "Column can support only following data types (varchar, tinytext, text).";
form.msg.departmentlist = "Column can support only following data types (varchar, tinytext, text).";
form.msg.grouplist = "Column can support only following data types (varchar, tinytext, text).";
form.msg.rolelist = "Column can support only following data types (varchar, tinytext, text).";
form.msg.richtextbox = "Column can support only following data type (longtext).";

// Jun'06 2013
form.error.emptydatadictionaryfield = "Data dictionary field should not be blank.";
form.error.formnamespace = "Form Name should not allow space.";
form.error.width ="Width should be a number.";
form.error.height ="Height should be a number.";
form.label.height ="Height (px) ";
form.label.width ="Width (px)";
form.msg.autocompletefield = "Column can support only following data types (varchar, tinytext, text).";
form.label.autocompletefield = "Autocomplete Field Properties";
form.label.chooseIcon = "Choose Icon";
form.label.removeIcon = "Remove Icon";
form.label.iconPath = "Icon Path";
table.title.sourceColumnName="Source Column";
table.title.targetColumnName="Target Column";
form.label.uploadedImagePath = "Uploaded Image Path";
form.label.uploadImage = "Upload Image";
form.label.removeImage = "Remove Image";
form.label.mouseEvents = "Mouse Events";
form.label.otherEvents = "Other Events";
form.label.defaultValue = "Default Value";
form.title.textboxProperties = 'Text Box Properties';
form.title.numberBoxProperties = 'Number Box Properties';
form.title.labelProperties = 'Label Properties';
form.label.buttonType = "Button Type";
form.title.userfield = 'User Field Properties';
form.label.basicProperties = 'Basic Properties';
form.title.rolefield = 'Role Field Properties';
form.title.departmentfield = 'Department Field Properties';
form.title.groupfield = 'Group Field Properties';
form.title.selectBox = 'Select Box Properties';
form.label.btnRemoveValue = "Remove as Selected Value";
form.label.btnDown = "▼"
form.title.btnDown = "Move down";
form.label.btnUp = "▲";
form.title.btnUp = "Move up";
form.title.tabDesign = "Tab Design";
form.label.tabTitle = "Tab Title";
form.msg.tabDesign = "You should give 'Tab Title' as comma(,) seperated."
	
//----jul23----
form.error.defaultvaluenumber ="Default value should be a number.";

form.label.onLoadEventName = "onLoad Event"
form.title.formOnLoadEventName = "Form onLoad Event"
	
form.msg.defaultButton = "Refer to Default Button."
	
	
	
//----------------user profile-------------------------
form.msg.passwordLength = "Password Length Should be atleast 6 characters."
form.msg.confirmPasswordNotEmpty = "Confirm Password Should not be Empty."
form.msg.confirmPasswordSameAsNewPassword = "Confirm Password Should be same as New Password."
form.msg.enterCurrentPassword = "Current password should not be empty."

//-----------holiday-----------------
form.msg.empty = "No day is selected, select atleast one day."
form.title.info = "Info"

//----------homepage-------
form.msg.homePageSavedSuccess = "Successfully saved home page."

//-----------widget------
form.msg.jspNotEmpty = "Import JSP should not be empty."
form.title.titleFileFormat = "Error In File Format"
form.msg.fileFormat = "Invalid File Format,Select only Jsp and HTML Files."
form.msg.listViewNotEmpty = "List View should not be empty."
form.msg.reportNotEmpty = "Report shoud not be empty."
form.msg.confirmToDeleteJsp = "Are you sure you want to delete this Jsp Form "
form.msg.confirmToDeleteAllVersionsOfForm = "Are you sure you want to delete all the versions of the Form: "
form.msg.cannotAddMoreThanSevenWidgets = "Cannot select more than 7 widgets."
form.msg.selectAtleastOneWidget = "Select atleast one widget."


//-------------------------For All Delete Methods-----------------------
form.title.deleteTrigger = "DeleteTrigger"
form.title.deleteWidget = "DeleteWidget"
form.title.deleteQuickNav = "DeleteQuickNavigation"
form.msg.selectAtleastSingleWidget = "Select atleast single widget to Delete."
form.msg.selectSingleTimingTask  = "Select atleast single timing task to Delete."
form.msg.selectAtleastSingleQuickNav = "Select atleast single Quick Navigation to Delete."
form.msg.confirmToDeleteTask = "Are you sure want to delete these task(s)?"
form.msg.confirmToDeleteWidget = "Are you sure want to delete these widget(s)?"
form.msg.confirmToDeleteQuickNav = "Are you sure want to delete these Quick Navigation(s)?"
form.msg.selectAtleastSingleEntry = "Select atleast single entry to Delete"
form.msg.confirmToDeleteSysConfig = "Are you sure want to delete these SysConfig?"
form.msg.confirmToDeleteMenu = "Are you sure want to delete this menu?"
form.msg.confirmToDeleteIcon = "Are you sure you want to delete this Icon ?"
form.msg.confirmToDeleteLogo = "Are you sure you want to delete this Logo ?"
form.msg.confirmToRemoveImage = "Are your sure want to remove the Image?"
form.msg.errorInDeleteTask = " Error in deleting task(s) "
form.msg.errorInDeleteWidget = " Error in deleting widget(s) "
form.msg.successInDeleteWidget = "Widget(s) deleted successfully."
form.msg.successInDeleteQuickNav = "QuickNavigation(s) deleted successfully."
form.msg.listViewNotPresent = "List view is not present."
form.msg.loginUserNotAllowedToDeleteThisLayout = "Login user is not allowed to delete this Layout."
form.msg.wantToDeleteWidget = "Do you want to delete the widget?"
form.msg.groupNameNotAsGroup = "Cannot create group as name group."
form.msg.selectValidRole = "Select any valid Role."
form.msg.selectValiddepartment = "Select any valid Department."
form.msg.errorInUpdatingUser = " Error in updating users. "
form.msg.successInUpdatingUser = "Selected users has been updated successfully. "
form.msg.selectValidRoleOrGroup = "Select valid Role/Group."
form.msg.cannotPullUsersToAllUsers = "You cannot pull users to All Users."
form.msg.someUsersAlreadyAdded = "Some of the selected user(s) are already added."
form.msg.selectUser = "Select a user."
form.msg.organizationOrderNotEmpty = "Organizer order should not be empty. "
form.msg.selectAtleastOneDeptOrRole = "Select atleast one Department or Role."

form.msg.cantDeleteActiveSysConfig = "You can't delete active Systemconfiguration."

form.msg.cantDeleteSysDefinedMenu = "You cannot delete system defined menu."
form.msg.selectMenu = "You should select menu."

form.msg.maximumImageSize = "Maximum allowed image size is (Width : 18px, Height : 18px)."
form.msg.maximumLogoSize = "Logo size should be (Width : 200px, Height : 45px)."
form.msg.uploadImageFileOnly = "Upload a image file only."

form.msg.cycleValueNotEmpty = "Cycle Value Should be an Integer Number and Not Empty."

form.msg.chooseExistingModule = "Choose the existing Module."

form.msg.confirmToDeleteJspForm = "Are you sure you want to delete this Jsp Form? "
form.msg.confirmToDeleteAllVersionsOfForm = "Are you sure you want to delete all the versions of the Form? "

form.msg.dictionaryValueNotEmpty = "Dictionary Values should not be empty."
form.msg.sqlNotEmpty = "Sql should not be empty."

form.msg.changeEffectInNextLogin = "The Change will be affect in next login."
form.msg.checkParentMenuFirst = "Check the parent menu first."

form.msg.urlNotValid = "Given URL is not valid."
form.msg.urlIsARequiredField = "URL is a required field."
form.msg.validUrl = "Given Url is not Valid"
form.msg.justExport = "Just export"

//------------------Process--------------------
form.msg.deselectSuspendStateProcess = "One or More process are already in Suspend state. Deselect it!"
form.msg.selectAtleastSingleProcessToSuspend = "Select atleast single process to Suspend!"
form.msg.deselectActiveStateProcess = "One or More process are already in Active state. Deselect it!"
form.msg.selectAtleastSingleProcessToActivate = "Select atleast single process to Activate!"
form.msg.selectAtleastSingleProcessToExport = "Select atleast single Process to Export!"
form.msg.selectAtleastSingleProcessToVersionExport = "Select atleast single Process Version to Export!"
form.msg.selectAtleastSingleProcessToDelete = "Select atleast single Process to Delete!"
form.msg.selectAtleastSingleProcessVersionToDelete = "Select atleast single Process Version to Delete!"
form.msg.confirmToDeleteAllProcess = "Are you sure want to delete all version of these Process Definitions?"
form.msg.confirmToDeleteSelectedProcessVersion = "Are you sure want to delete selected Process Versions?"
form.msg.selectProcessToDownload = "Select atleast single process to Download!"
form.msg.selectAtleastSingleProcessInstanceToDelete = "Select atleast single Process Instance to Delete."
form.msg.contDeleteAnyOfSelectedInstanceIsOperated = "You cannot Delete. Any of the selected Instance is Operated"
form.msg.confirmToDeleteSelectedProcessInstances = "Are you sure you want to delete selected process Instances?"
form.msg.confirmToDeleteThisProcessInstance = 'Are you sure you want to delete this process instance?'
form.msg.contDeleteSelectedProcessIsOperated = "You cannot Delete.Selected Instance is Operated"
form.msg.confirmToTerminateProcessInstance = "Are you sure you want to terminate this process instance?"
form.msg.unsavedDataWillBeLostConfirmToContinue = "Unsaved Data will be lost. Are you sure want to Continue ?"
//process operating functions

form.msg.listViewColumnProperty = "You should change any one of list view columns property or No records in list view columns property."
form.title.msgBoxTitleInfo = "Info"
form.title.msgBoxTitleinfo = "info"
form.title.msgBoxTitleSuccess = "Success"
form.title.msgBoxTitlesuccess = "success"
form.title.msgBoxTitleerror = "error"
form.msg.listViewVewrsionDelete = "Are you sure want to delete selected List View Versions?"
form.title.msgBoxTitleconfirm = "confirm"
form.msg.deleteListView = "Select atleast single List View to delete"
form.msg.tableExport = "Table Export"
form.title.listViewParams = "List View Params"
form.msg.activeButton = "Button is in Active  State, If you wat to Inactivate this deselect the status check box."
form.msg.inActiveButton = "Button is in InActive  State, If you want to activate this select the status check box."
form.msg.confirmToDeleteSelectedColumns = "Confirm to delete this selected Column?"
form.msg.confirmToDeleteAllColumns = "Confirm to delete All Columns?"
form.msg.confirm = "Do you want to " 
form.msg.adduser = "Add user"
form.msg.select = "Select atleast one instance to"
form.msg.replaceError = "Replace not possible.Select instances of same node type"
form.msg.operationError = "All or some of the instances may be a start node or may be supended or terminated or withdrawn or deleted"
form.msg.terminateError = "All or some of the instances may be terminated"
form.msg.mandatoryMsg = "Fill all the required fields"
form.msg.activateMsg = "Process instance is suspended.Activate it"
form.msg.javaEventSuccess = "Java Event executed successfully"
form.msg.addUserMandatory = "User should not be empty"
form.msg.organizerManadatory = "Organizers should not be empty"
form.msg.selectTransactorsForTask = "Select Transactors for Next Task"
form.msg.noOrganizer = "No organizer,co-ordinator element found!"
form.msg.noForm = "No form element found!"
form.msg.selectTransactorsForStartProcess = "Select Transactors for Start Process"
form.msg.processStartError = "Problem in starting the process"
form.msg.formMandatoryForProcessStart = "Form mandatory to start process"
form.msg.noPrevilage = "No Privilege to Remove"
	
form.msg.queryValidation = "Query should not be empty."
form.msg.querySuccess = "Query executed successfully."
form.msg.queryDropQuery = "Drop Query is not allowed."
form.msg.listViewRestoreRecord = "Are you sure you want to restore selected record?"
form.msg.listViewRestrictRestorePermission = "You are not having permission to restore the List View."
form.msg.listViewSelectColumn = "Select the Table and Column name in List View for Delete and Restore functionality"
form.msg.listViewDeleteRecord = "Are you sure you want to delete selected record?"

form.msg.permissionRestricted = "No privilege to delete"
form.msg.listViewRestoreVersion = "Restore List View Version"
form.msg.listViewActiveListVersion = "Currently active view version will be in-activated, Are you sure want to activate this View version?"
form.msg.listViewExport = "No data to export"
form.msg.tableSelectFirst = "Tables have to select first"
form.msg.listViewError = "Problem in getting child nodes"
form.msg.listViewEntityError = "List view entity is not found" 
form.msg.listViewGroupSettingChanges = "You should change any one of list view group setting property or No records in list view group setting property."
form.msg.listViewNoRecords = "No Records."
form.msg.listViewSelectRow = "Select row."
form.msg.listViewRemoveChild = "This is a parent group,  first remove the child group."
form.msg.listViewGroupFields = "Group name and Group field name should not be blank."
form.msg.listViewRestrictedCharacters = "Special Character(s) are not allowed in Group name."
form.msg.listViewOrderNumber = "Display Order shoud be an number or already exist."

form.msg.listViewErrorGroupName = "Group name already exist."
form.msg.listViewErrorSameParentName = "Group field and Parent group should not be same."
form.msg.listViewErrorListView = "The List view is not present."
form.msg.listViewSuccessTaskDelete = "Task(s) deleted successfully"
form.msg.listViewErrorContainerLoad = "No container found for loading listview: "
form.msg.listViewExitProcessDesigner = "Unsaved Data will be lost. Are you sure want to Continue ?"
form.msg.cannotDeleteDefaultButtons = "You can't delete the default button."
form.msg.confirmToDeleteSelectedButton = "Confirm to delete selected Button?"
form.msg.listViewErrorDisplayName = "Display name already exist."
form.msg.listViewErrorOrderNumber = "Display Order shoud be an number  or already exist."
form.msg.listViewErrorButtonScript = "Button Script should not be blank." 
form.msg.listViewErrorRestrictedCharacters = "Special Character(s) are not allowed in Display name."
form.msg.listViewErrorEmptyName = "Display Name should not be blank."
form.msg.listViewValidationNumber = "Display Order shoud be an Number"
form.msg.confirmToDeleteAllButtons = "Confirm to delete all Buttons ?"
form.msg.allButtonsDeletedSuccessfully = "All Buttons deleted successfully"
form.msg.selectedButtonDeletedSuccessfully = "Selected button deleted successfully"
form.msg.confirmToDeleteAllGroupSettings = "Confirm to delete all group settings?"
form.msg.allGroupSettingsDeletedSuccessfully = "All Group settings deleted successfully"
form.msg.confirmToDeleteSelectedGroupSetting = "Confirm to delete selected group settings"
form.msg.selectedColumnDeletedSuccessfully = "Selected Column deleted Successfully."
form.msg.allColumnDeletedSuccessfully = "All Columns deleted Successfully."
form.msg.nonDefaultButtonsDeletedSuccessfully = "All Non default buttons deleted successfully."
form.msg.selectedGroupSettingsDeletedSuccessfully = "Selected Group Setting Deleted Successfully."
//------------------roles-----------------------
form.msg.cannotDeleteSystemRole = "You cannot delete 'System' Role"
form.msg.selectAtleastSingleRoleToDelete = "Select atleast single role to Delete"
form.msg.confirmToDelete = "Are you sure want to delete these roles?"
form.msg.confirmToDeleteThis = "Are you sure you want to delete this "
form.msg.confirmDelete = "Are you sure you want to delete "
form.msg.confirmToRestore = "Are you sure you want to restore "


//----------------------------user------------------
form.msg.selectUserToDelete = "Select atleast single User to Delete"
form.msg.confirmToDeleteUser = "If this user(s) has any positions in a group or department, they will become vacant. \n Are you sure want 					to delete the user(s)?",
form.msg.someSelectedUsersAreAlreadyDisabled = "Some of the selected user(s) are already disabled"
form.msg.confirmToDisable = "Are you sure want to disable these users?"
form.msg.selectAtleastSingleUserToDisable = "Select atleast single user to Disable"

//------------------roles-----------------------
form.msg.cannotDeleteSystemRole = "You cannot delete 'System' Role"
form.msg.selectAtleastSingleRoleToDelete = "Select atleast single role to Delete"
form.msg.confirmToDelete = "Are you sure want to delete these roles?"



form.msg.selectAtleastSingleFileToDelete = "Select atleast single file to Delete"
form.msg.confirmToDeleteFiles = "Are you sure want to delete these Files ?"

form.msg.confirmToDeleteDataDictionary = "If this datadictionar(y/ies) or its Children Dictionaries has any positions in a forms , \n they will become vacant or deleted. Are you sure want to delete?"
form.msg.selectAtleastSingleDataDictionaryTo = "Select atleast one DataDictionary to "
form.msg.someDataDictionaryAreAlready = "Some of the DataDictionaries are already "
form.msg.d = "d"
form.msg.dataDictionaryOperationSuccess = "Selected DataDictionaries has been successfully "
form.msg.cannotUpdateDictionaryOrder = "Dictionary Order cannot be updated."
form.msg.cannotUpdateDepartmentOrder = "Department Order cannot be updated."
form.msg.cannotUpdateGroupOrder = "Group Order cannot be updated."
form.msg.cannotUpdateRoleOrder = "Role Order cannot be updated."

form.msg.selectAtleastSingleGroupToDelete = "Select atleast single group to Delete."
form.msg.confirmToDeleteGroup = "Are you sure want to delete these groups?"

form.msg.cannotDeleteRootDepartment = "You cannot delete root Department."
form.msg.selectAtleastSingleDepartmentToDelete = "Select atleast single department to Delete."
form.msg.confirmToDeleteDepartment = "Are you sure want to delete these departments?"

form.msg.selectRowsToExport = "Select rows to export."
form.msg.selectAtleastSingleRowToDelete = "Select atleast single row to Delete."
form.msg.selectAtleastSingleInstanceToExport = "Select atleast single instance to Export!"

form.msg.selectAtleastSingleWordToDelete = "Select atleast single word to Delete."
form.msg.confirmToDeleteWord = "Are you sure want to delete these Words ?"

form.msg.confirmToDeleteLogs = "Are you sure want to delete these Logs ?"

form.msg.confirmToDeleteReports = "Are you sure want to delete these Reports ?"


form.title.msgBoxTitleMessage = "Message"
form.title.msgBoxTitleTableName = "Table Name"
form.title.msgBoxTitleCheck = "Check"
form.title.msgBoxTitleError = "Error"
form.title.msgBoxTitleQueryPreview = "Query Preview"
form.title.msgBoxTitleModuleName = "Module Name"
form.title.msgBoxTitleConfirm = "Confirm"
form.title.msgBoxTitleSuccess = "Success"
form.title.information = "Information"
form.title.msgBoxTitleValidation = "Validation"

form.title.dataDictionaryList = "Data Dictionary List"


form.msg.listviewQueryFromSqlError = "From Sql shoud not be empty"
form.msg.listviewQuerySelectColumn = "Select Columns shoud not be empty"
form.msg.listViewErrorListViewName = "List view name should not contains Special Characters other than Underscore_ ."
form.msg.listViewColumnTitleErrorNameExist = "Column Title name already exist."
form.msg.listViewColumnTitleErrorPosition = "Column position is invalid."
form.msg.listViewColumnTitleErrorRestrictedCharacters = "Special Character(s) are not allowed in Column Title."
form.msg.listViewColumnTitleErrorNotEmpty = "Column Title and Date Fields should not be blank."
form.msg.columnAddedSuccessfully = "Column Added Successfully."
form.msg.buttonAddedSuccessfully = "Button Added Successfully."
form.msg.groupAddedSuccessfully = "Group Added Successfully."

form.title.moduleChoose = "Choose the existing Module"
form.msg.copyErrorSelect = "Select a Form to copy"
form.msg.moduleCopy = "Select a Module to copy"
form.msg.mandatoryName = "Form name is mandatory."
form.title.copyForm = "Copy Form"
form.title.copyFormType = "prompt"
form.title.copyFormError = "One Form can be copy at a time"
form.msg.errorChildNotExist = "The Does not have any Child"
form.msg.buttonDelete = "Are you sure you want to delete this Button ?"

form.title.success = "Success"
form.title.msgBoxTitleMessage = "Message"
form.title.msgBoxTitleTableName = "Table Name"
form.title.msgBoxTitleCheck = "Check"
form.title.msgBoxTitleError = "Error"
form.title.msgBoxTitleQueryPreview = "Query Preview"
form.title.msgBoxTitleModuleName = "Module Name"
form.title.msgBoxTitleConfirm = "Confirm"
form.title.msgBoxTitleSuccess = "Success"
form.title.msgBoxTitleValidation = "Validation"
form.error.moduleNameRequired = "Name is required field."
form.error.moduleNameValidation = "Intial Numbers and special characters are not allowed for Module name."
form.error.moduleOrderValidation = "Module Order should be a Number."
form.error.moduleOrderNotZero = "Module Order number should be greater than 0."
form.error.tableNameExist = "Table name already exists!"
form.error.tableNameValidation = "Table Name can suport only up to 56 characters."
form.error.tableNameSpecialCharacterValidation = "Table Name should not contain special characters except underscore."
form.error.tableNameEmptyValidation = "Table Name should not be empty."
form.error.moduleNameEmptyValidation = "Module Name should not be empty."
form.error.relationPropertiesValidation = "Save the newly added column(s) before going to relation properties."
form.error.fieldPropertiesValidation = "Field Properties shoud not be NULL."
form.msg.atRowNo = "at Row no:"
form.error.notNull = "shoud not be NULL."
form.msg.fieldNo = "Field name at Row no:"
form.error.noSpace = "shoud not be an space(or null)"
form.msg.chineseNameAtRowNo = "Chinese Name at Row no:"
form.error.notANumber = "should not be a number"
form.error.notBeEmpty = "should not be empty"
form.msg.duplicateFieldName = "Duplicate Field Name"	
form.error.illegalChar = "contains illegal characters."
form.msg.defaultValueOf = "Default Value of"
form.error.shouldNumber = "shoud be a number check at Row no:"
form.error.validTime = "shoud be a valid time check at Row no:"
form.error.validTimeFormat = "shoud be in this format: 00:00:00.Check at Row no:"
form.error.validateDateFormat = "shoud be in this format: 0000-00-00.Check at Row no:"
form.error.validDateTimeFormat = "shoud be in this format: 0000-00-00 00:00:00.Check at Row no:"
form.msg.defaultValue = "Default Value"
form.error.greaterFieldLength = "is greater than the Field Length"
form.error.characterRange = "range shoud be 1 to 255"
form.error.yearRange = "range shoud be 4"
form.msg.lengthAtRowNo = "Length at Row no:"
form.msg.tempColumnSizeRange = "range shoud be 1 to 65"
form.error.numberValidation = "shoud be an Number"
form.msg.selectTableToEdit = "Select a Table to Edit"
form.msg.moduleSelectTableToEdit = "It is a Module, select a Table to Edit"
form.msg.selectTableToExport = "Select a Table to Export"
form.msg.moduleSelectTableToExport = "It is a Module, select a Table to Export"
form.msg.selectTableToDelete = "Select a Table to delete"
form.msg.moduleSelectTableToDelete = "It is a Module, select a Table to delete"
form.error.agentValidation = "Agent should not be blank."
form.msg.loginUser = "Login user"
form.error.cantBeAgent = "cannot be set as Agent."
form.error.startDateEmptyValidation = "Start Date should not be blank."
form.error.endDateEmptyValidation = "End Date should not be blank."
form.error.startDateLesserValidation = "Start Date should be lesser than End Date."
form.error.selectNotificationType = "Select at least one Notification type"
form.error.invalidFileFormat = "Invalid File Format"
form.error.documentFormNameExist = "Document Form Name Already exists"
form.error.documentFormNameSpecialCharacterValidation = "Document Form Name should not contain special characters"
form.error.documentFormNameEmptyValidation = "Document Form Name should not be Empty"
form.error.noPrivilege = "No Privilege to Remove"
form.error.homePageReport = "The given Report Url is Wrong.Make Sure the given URL is correct"

//------------ck editor plugins --------------------------------------
form.title.showButton = "Show Button"
form.title.labelDateTimePicker = "Date Time Picker"
form.msg.datePicker = "Date Picker"
form.msg.single = "Single"
form.msg.multiple = "Multiple"
form.title.pluginFileUpload = "File Upload"
form.title.pluginAddButtonTitle = "Are you want to add this button in form buttons ?"
form.msg.pluginUpdateButtonManager = "Update to button manager and this form"
form.msg.pluginAddForm = "Add to this form only"
form.msg.pluginAddButtonManager = "Add to button manager and this form"
form.title.pluginCreateForm = "You should create form."
form.title.pluginStatic = "Static"
form.title.pluginDynamic = "Dynamic"

form.title.pluginDataDictionaryId = "Data Dictionary Id"
form.title.gridViewControl = "Gridview Control"
form.title.pluginLocalImageUpload = "Local Image Upload"
form.msg.pluginImageFormat = "Only '.jpeg','.jpg', '.png', '.gif', '.bmp' formats are allowed."
form.msg.pluginImageDelete = "clicked to remove the image path"
form.msg.pluginSelectError = "Option text should not be blank."
form.msg.pluginSelectValueError = "Option value should not be blank."
form.title.pluginNUmberField = "Number Field"
form.title.pluginLabel = "Label"
form.msg.pluginNumber = "Number"
form.msg.pluginDecimal = "Decimal"
form.title.pluginOnloadEvent = "On Load Event"

form.title.pluginRichTextBox = "Rich Textbox"
form.title.pluginTabControl = "Tab Control"
form.msg.pluginTemplateImage = "Image and Title"
form.msg.pluginTemplateDescription = "One main image with a title and text that surround the image."
form.msg.pluginTemplateTextDescription = "A template that defines two colums, each one with a title, and some text."
form.title.pluginTemplateStrange = "Strange Template"
form.title.pluginTemplateText = "Text and Table"
form.msg.pluginTemplateTextAndTable = "A title with some text and a table."

form.msg.permission = "Permission not allowed, You can't do this."
form.msg.modulePermission = "You can't do this"
form.msg.moduleDeletePermission = "You cannot delete a System Module Table"
form.msg.seleteListViewEdit = "Select a List view to Edit"
form.msg.deleteError = "You cannot delete a System Module List View"
form.msg.deletedListView = "List View deleted successfully."
form.msg.restoredListView = "List View restored successfully."
form.msg.erroeDeleteListView = "Error occur while delete the List View."
form.msg.errorRestoreListView = "Error occur while restore the List View.."

form.msg.listViewDelete = "Select a List View to delete"
form.msg.errorModuleDelete = "You cannot delete a System Module"
form.msg.selectModuleDelete = "Select a Module to delete"
form.msg.exportModule = "Select a Module to Export"
form.msg.jspFormUpload = "Jsp uploaded successfully"
form.msg.faildUploadJsp = "Failed to upload jsp"


//----------------------msgBox-------------------
form.msg.form = " form ?"
form.msg.deletedSuccess = " form deleted successfully."
form.msg.restoredSuccess = " form restored successfully."
form.msg.problemWhileDelete = "Proplem occur while delete "
form.msg.problemWhileRestore = "Proplem occur while restore "
form.msg.formm = " form."
form.msg.confirmToDeleteTheseFormVersions = "Are you sure want to delete these form version(s)?"
form.msg.confirmToDeleteTheseForms = "Are you sure want to delete these Form(s)?"
form.msg.canotDeleteActiveVersionOfForm = "You cannot delete Active version of form So deselect the Active version."
form.msg.activeFormWillBeDeactivatedConfirmToActivateThisFormVersion = "Currently active form version will be in-activated, Are you sure want to activate this form version?"
form.msg.activeFormWillBeDeactivatedConfirmToActivateThisProcessVersion = "Currently active process version will be in-activated, Are you sure want to activate this Process version?"
form.msg.unaleToDeleteActiveVersions = "Some of the selected version(s) is in Active state. Unable to delete the active versions."
form.msg.confirmToDeleteThisProcessVersion = "Are you sure you want to delete this process version?"
form.msg.confirmToDeleteAllPricessDefinitions = "Are you sure you want to delete all the versions of this process definition?"
form.msg.confirmToRemoveMember = "Are you sure want to remove the member?"
form.msg.wantToSubtitute = "Are you sure want to substitute : "
form.msg.selectedTask = " to the selected task?"
form.msg.pleaseSpecifyAFormName = "Specify a Form Name."
form.msg.formName = "Form Name"
form.msg.uploadValidFileFormate = "Upload a valid file of format .bpmn20.xml."
form.msg.uploadValidFileFormatexls = "Upload a valid file of format .xls"
form.msg.uploadValidFileFormatesql = "Upload a valid file of format .sql"
form.msg.formNotEmpty = "Form Name should not be empty."
form.msg.formNameAlreadyExists = "Form Name already exists!"
form.msg.availableUserShouldNotBeEmpty = "Available user should not be empty"
form.msg.userAlreadyAddedAs = "User has been already added as "
form.msg.PleaseEnterClassyName = "Enter classification name"
form.msg.classificationAddedSuccessfully = "Classification added successfully"
form.msg.numbersAndSplCharsNotAllowed = "Numbers and special characters are not allowed for classification name"
form.msg.giveDifferentFormName = "Give a different Form Name."
form.msg.pleaseChooseFileLocation = "Choose the file location"
form.msg.chooseExistingModule = "Choose the existing Module"
form.msg.scriptError = "Script Error: No form found on page with id: "
form.msg.selectTableName = "Select table name"
form.msg.moduleShouldNotBeEmpty = "The module should not be empty."
form.msg.selectFileLocation = "Select file location "
form.msg.addedToQuickNav = " added to quick navigation"
form.msg.removededToQuickNav = " removed to quick navigation"
form.msg.selectgSourceAndTargetColumn = "select a source and target column"
form.msg.chooseExistingColumn  = "Choose the existing columns."
form.msg.userDoesntHavePrevilegeToSubmit = "User does not have previlege to submit"
form.msg.tableShouldNotBeEmpty = "The table should not be empty"
form.msg.selectAtleastSingleNewsToDelete = "Select atleast single news to Delete"
form.msg.confirmToDeleteNews = "Are you sure want to delete these News?"
form.msg.selectAtleastSingleNoticeToDelete = "Select atleast single notice to Delete"
form.msg.selectAtleastSingleFormToDelete = "Select atleast single Form to Delete."
form.msg.noEmptyListView = "No Form empty list view." 
form.msg.pleaseSelectAProcess = "Select a process"
form.msg.processName = "Process name" 
form.msg.alreadyHasAgencySetting = "already has Agency Setting"
form.msg.confirmToRemoveAgencySetting = "Are you sure want to remove Agent Setting(s)?"
form.msg.pleaseSelectAProcessWithAgencySetting = "Select a process with Agency Setting "
form.msg.selectAtleastSingleProcessToRead = "Select atleast single process to Read"
form.msg.selectSingleRowToPrint = "Select only one row to Print"
	

	
// for documents added on 11-4-2014

form.msg.deleteConfirm = "Do you want to delete ?"
form.msg.selectFolder = "Select any folder"
form.title.noPermission = "You do not have Write Permission"
form.msg.noWritePermission = "No Permission"		
form.msg.errorInGettingChildNodes = "Problem in getting child nodes"
form.msg.userMandatory = "User should not be empty"
form.msg.roleMandatory = "Role should not be empty"
form.msg.departmentMandatory = "Department should not be empty"
form.msg.groupMandatory = "Group should not be empty"
form.msg.permissionMandatory = "Permission field should not be empty"
form.msg.opnionMandatory = "Opinion should not be empty"



// For select box

form.label.btnDelete = "Delete"
form.label.btnAdd = "Add"
form.label.btnModify = "Modify"
form.label.btnSetAsSelectedValue = "Set as selected value"
