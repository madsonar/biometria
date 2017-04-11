; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{73525008-4482-4BA3-A097-BC9001D53DA8}
AppName=Biometria Nitgen
AppVersion=1.0
;AppVerName=Biometria Nitgen 1.0
AppPublisher=Rtools Sistemas
AppPublisherURL=http://www.rtools.com.br/
AppSupportURL=http://www.rtools.com.br/
AppUpdatesURL=http://www.rtools.com.br/
DefaultDirName={pf}\Biometria Nitgen
DisableProgramGroupPage=yes
InfoBeforeFile=C:\rtools\Biometria\lib\TUTORIAL WINDOWS.txt
InfoAfterFile=C:\rtools\Biometria\lib\LEIA-ME.txt
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Languages]
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\rtools\Biometria\lib\*"; DestDir: "{app}\lib\"; Flags: ignoreversion recursesubdirs createallsubdirs
; Source: "C:\rtools\Biometria\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
;Source: "C:\rtools\Biometria\lib\*"; DestDir: "{app}"; Flags: ignoreversion 
Source: "C:\rtools\Biometria\Biometria.exe"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\Biometria Nitgen"; Filename: "{app}\Setup.exe"
Name: "{commondesktop}\Biometria Nitgen"; Filename: "{app}\Setup.exe"; Tasks: desktopicon

[Run]
Filename: "{app}\Biometria.exe"; Description: "{cm:LaunchProgram,Biometria Nitgen}"; Flags: nowait postinstall skipifsilent

