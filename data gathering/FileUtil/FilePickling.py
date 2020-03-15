import pickle
import json
import os
import glob
from os import path
from datetime import datetime
import shutil
from YALogger.custom_logger import Logger


def save_obj(obj, name, directory, json_save_needed):
    directory_path = os.getcwd() + "/" + directory + "/"
    if not path.exists(directory_path):
        os.mkdir(directory_path)

    timestamp = datetime.now().strftime("%Y-%m-%d")
    full_data_file_path = directory_path + name + "_" + timestamp + ".pkl"
    if path.exists(full_data_file_path) == False:
        with open(full_data_file_path, "wb") as f:
            pickle.dump(obj, f, pickle.HIGHEST_PROTOCOL)
        if json_save_needed:
            if type(obj) == dict:
                full_json_data_file_path = (
                    directory_path + name + "_" + timestamp + ".json"
                )
                with open(full_json_data_file_path, "w") as fp:
                    json.dump(obj, fp)
            else:
                Logger.log(
                    "error",
                    "FilePickling",
                    "save_obj",
                    "Data cannot be saved as json as its not a dict",
                )
    else:
        Logger.log(
            "error", "FilePickling", "save_obj", full_data_file_path + " already exists"
        )


def load_obj(name, directory):
    directory_path = os.getcwd() + "/" + directory + "/"
    timestamp = datetime.now().strftime("%Y-%m-%d")
    full_data_file_path = directory_path + name + "_" + timestamp + ".pkl"
    if path.exists(full_data_file_path) == True:
        with open(full_data_file_path, "rb") as f:
            return pickle.load(f)
    else:
        raise IOError(full_data_file_path + " doesnt exist")


def load_latest_obj(name, directory):
    directory_path = os.getcwd() + "/" + directory + "/*.pkl"  # all pickle files
    list_of_files = glob.glob(directory_path)
    latest_file = max(list_of_files, key=os.path.getctime)
    with open(latest_file, "rb") as f:
        return pickle.load(f)
