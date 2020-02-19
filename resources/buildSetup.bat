echo %1
echo %2
echo %3

set trigger=%3
echo trigger %trigger%

set top_level_config=%1\build.toml
set default_within_folder_config=%1\build_config\default.toml
set branch_config_file=%1\build_config\%2.toml
set branch_timer_config_file=%1\\build_config\\%2_t.toml
set final_config_file=%top_level_config%
echo %top_level_config%

if exist %top_level_config% (
    echo file %top_level_config% exists
) else (
   	 echo Top level file %top_level_config% does not exist, config is expected in build_config folder
    	if %3==timer (
    			echo Build has been triggered by timer, expected file name is %2_t.toml
			if exist %branch_timer_config_file% (
				echo Branch config file for time triggered builds exists: %branch_timer_config_file%
				set final_config_file=%branch_timer_config_file%
			) else (
				echo Branch config file for time triggered builds does not exist, default file will be used: %default_within_folder_config%
				set final_config_file=%default_within_folder_config%
			)
	)else (
			echo Build has not been trigger by timer, expected file name is %2.toml
			if exist %branch_config_file% (
    							echo Branch config file exists: %branch_config_file%
							set final_config_file=%branch_config_file%
			) else (
				echo Branch config file does not exist, default file will be used: %default_within_folder_config%
				set final_config_file=%default_within_folder_config%
			)
	)
)

echo Very final config file path being used: %final_config_file%
echo copying file from %final_config_file% to %top_level_config%
copy /Y %final_config_file% %top_level_config%

@echo off
set "venv_path=env"

pip install virtualenv

virtualenv %venv_path%

call %venv_path%\\Scripts\\activate.bat

pip install toml
python niveristand-custom-device-build-tools\\resources\\toml2json.py %top_level_config%

call %venv_path%\\Scripts\\deactivate.bat

rmdir /s /q %venv_path%

@echo on