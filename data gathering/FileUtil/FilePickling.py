""" pylint missing-module-docstring-disable """
import pickle
import json
import os
import glob
from os import path
from datetime import datetime
from YALogger.custom_logger import Logger


def save_obj(obj, name, directory):
    # pylint: disable=missing-function-docstring
    directory_path = os.getcwd() + "/" + directory + "/"
    if not path.exists(directory_path):
        os.mkdir(directory_path)

    timestamp = datetime.now().strftime("%Y-%m-%d")
    full_json_data_file_path = directory_path + name + "_" + timestamp + ".json"
    if not path.exists(full_json_data_file_path):
        if isinstance(obj, (dict, list)):
            with open(full_json_data_file_path, "w") as json_file:
                json.dump(obj, json_file)
        else:
            Logger.log(
                "error",
                "FilePickling",
                "save_obj",
                "Data cannot be saved as json as its not a dict",
            )
    else:
        Logger.log(
            "error",
            "FilePickling",
            "save_obj",
            full_json_data_file_path + " already exists",
        )


def load_obj(name, directory):
    # pylint: disable=missing-function-docstring
    directory_path = os.getcwd() + "/" + directory + "/"
    timestamp = datetime.now().strftime("%Y-%m-%d")
    full_data_file_path = directory_path + name + "_" + timestamp + ".pkl"
    if path.exists(full_data_file_path):
        with open(full_data_file_path, "rb") as data_file:
            return pickle.load(data_file)
    else:
        raise IOError(full_data_file_path + " doesnt exist")


def load_latest_obj(directory):
    # pylint: disable=missing-function-docstring
    directory_path = os.getcwd() + "/" + directory + "/*.pkl"  # all pickle files
    list_of_files = glob.glob(directory_path)
    latest_file = max(list_of_files, key=os.path.getctime)
    with open(latest_file, "rb") as data_file:
        return pickle.load(data_file)
